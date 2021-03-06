package xyz.lightseekers.maven_blog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.lightseekers.maven_blog.bean.Token;
import xyz.lightseekers.maven_blog.bean.TokenExample;
import xyz.lightseekers.maven_blog.bean.User;
import xyz.lightseekers.maven_blog.bean.UserExample;
import xyz.lightseekers.maven_blog.bean.ex.RoleEX;
import xyz.lightseekers.maven_blog.bean.ex.TokenEX;
import xyz.lightseekers.maven_blog.mapper.TokenMapper;
import xyz.lightseekers.maven_blog.mapper.UserMapper;
import xyz.lightseekers.maven_blog.mapper.ex.RoleEXMapper;
import xyz.lightseekers.maven_blog.mapper.ex.UserEXMapper;
import xyz.lightseekers.maven_blog.service.IUserService;
import xyz.lightseekers.maven_blog.util.AuthUtil;
import xyz.lightseekers.maven_blog.util.RandomImgCodeUtil;
import xyz.lightseekers.maven_blog.util.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleEXMapper roleEXMapper;

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private UserEXMapper userEXMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String UPLOAD_PATH_PREFIX = "static/image/user/";
    public final static String LOOK_PATH_PREFIX = "/image/user/";

    @Value("${fromMail}")
    private String from;

    @Override
    public String loginByEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        String code = RandomImgCodeUtil.getStringRandom(4);
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("验证码");
        message.setText(code);
        try {
            javaMailSender.send(message);
            logger.info("简单邮件已经发送。");
            return code;
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
        return null;
    }

    @Override
    public String loginFace(MultipartFile uploadFile, HttpServletRequest request, Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        String filePath = null;
        if (uploadFile.isEmpty()) {
            //返回选择文件提示
            return "未上传图片";
        }
        //构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
        File file = new File(realPath);
        File newFile = null;
        String oldFile = new File("src/main/resources/static/").getAbsolutePath() + user.getImg();
        if (!file.isDirectory()) {
            //递归生成文件夹
            file.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        System.out.println(oldFile);
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            //构建真实的文件路径
            newFile = new File(file.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            uploadFile.transferTo(newFile);
            user.setImg(LOOK_PATH_PREFIX + newName);
            filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + user.getImg();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(newFile.getAbsolutePath());
        String s = AuthUtil.faceMatch(AuthUtil.checkSession(), oldFile, newFile.getAbsolutePath());
        newFile.delete();
        return s;
    }

    /**
     * 登录校验
     *
     * @param username 前端获取的用户账号
     * @param password 用户输入的密码
     * @return 成功返回Token 失败返回null
     * @throws RuntimeException
     */
    @Override
    public TokenEX login(String username, String password) throws RuntimeException {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 0) {
            return null;
        } else if (users.get(0).getPassword() == password || password.equals(users.get(0).getPassword())) {
            TokenExample tokenExample = new TokenExample();
            tokenExample.createCriteria().andUserIdEqualTo(users.get(0).getId());
            List<Token> tokens = tokenMapper.selectByExample(tokenExample);
            RoleEX roleEX = roleEXMapper.selectById(users.get(0).getRoleId());
            TokenEX tokenEx = new TokenEX();
            Token token = null;
            if (tokens.size() == 0) {
                token = new Token();
                token.setToken(TokenUtil.createToken(username, String.valueOf(new Date().getTime())));
                token.setUserId(users.get(0).getId());
                token.setDate(new Date());
                tokenMapper.insert(token);
            } else {
                token = tokens.get(0);
            }
            tokenEx.setToken(token.getToken());
            tokenEx.setUserId(token.getUserId());
            tokenEx.setName(users.get(0).getName());
            tokenEx.setUserName(users.get(0).getUsername());
            tokenEx.setRoleId(roleEX.getId());
            tokenEx.setRoleName(roleEX.getName());
            return tokenEx;
        } else {
            return null;
        }
    }

    /**
     * 修改密码
     *
     * @param id          userid
     * @param oldPassword 旧密码
     * @param NewPassword 新密码
     * @return 1表示成功，0表示失败:旧密码输入错误
     * @throws RuntimeException
     */
    @Override
    public int changePassword(int id, String oldPassword, String NewPassword) throws RuntimeException {
        User user = userMapper.selectByPrimaryKey(id);
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(NewPassword);
            userMapper.updateByPrimaryKey(user);
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * 注册
     *
     * @param username 用户账号 唯一*
     * @param password 用户密码
     * @param email    用户邮箱
     * @param name     用户昵称
     * @return 1注册成功 0注册失败
     * @throws RuntimeException
     */
    @Override
    public int register(String username, String password, String email, String name) throws RuntimeException {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 0) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            user.setFlag(1);
            user.setRoleId(2);
            return userMapper.insert(user);
        } else {
            return 0;
        }


    }

    /**
     * 根据id查找用户信息
     *
     * @param id 用户id
     * @return user类
     * @throws RuntimeException
     */
    @Override
    public User selectById(int id) throws RuntimeException {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     * @throws RuntimeException
     */
    @Override
    public int deleteById(int id) throws RuntimeException {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteMany(int[] id) throws RuntimeException {
        for (int i = 0; i < id.length; i++)
            deleteById(id[i]);
        return id.length;
    }

    /**
     * 增加或修改用户信息
     *
     * @param user user.id 由系统自增长
     * @return 操作条数 0则 新增失败：username重复
     * @throws RuntimeException
     */
    @Override
    public int addOrUpdate(User user, MultipartFile uploadFile, HttpServletRequest request) throws RuntimeException {
        if (user == null) {
            throw new RuntimeException();
        }
        if (user.getId() == null) {
            String filePath = null;
            if (uploadFile.isEmpty()) {
                //返回选择文件提示
                return 0;
            }
            //构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
            String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
            File file = new File(realPath);
            if (!file.isDirectory()) {
                //递归生成文件夹
                file.mkdirs();
            }
            String oldName = uploadFile.getOriginalFilename();
            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
            try {
                //构建真实的文件路径
                File newFile = new File(file.getAbsolutePath() + File.separator + newName);
                //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
                uploadFile.transferTo(newFile);
                user.setImg(LOOK_PATH_PREFIX + newName);
                filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + user.getImg();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (user.getId() == null) {
            UserExample example = new UserExample();
            example.createCriteria().andUsernameEqualTo(user.getUsername());
            List<User> users = userMapper.selectByExample(example);
            if (users.size() == 0) {
                user.setFlag(1);
                user.setRoleId(2);
                return userMapper.insert(user);
            }
            return 0;
        } else {
            User user1 = selectById(user.getId());
            user.setFlag(user1.getFlag());
            user.setRoleId(user1.getRoleId());
            return userMapper.updateByPrimaryKey(user);
        }


    }

    /**
     * 列出所有用户信息
     *
     * @return 所有用户的list集合
     * @throws RuntimeException
     */
    @Override
    public List<User> selectAll() throws RuntimeException {
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }

    /**
     * 给用户分配角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 规范值
     * @throws RuntimeException
     */
    @Override
    public int assignRoles(int userId, int roleId) throws RuntimeException {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setRoleId(roleId);
        return userMapper.updateByPrimaryKey(user);
    }

    /**
     * 禁用或启用用户
     *
     * @param id     用户id
     * @param choose true不禁用|false禁用
     * @return 常规值
     * @throws RuntimeException
     */
    @Override
    public int openOrCloseUser(int id, boolean choose) throws RuntimeException {
        User user = userMapper.selectByPrimaryKey(id);
        if (choose == true) {
            user.setFlag(1);
        } else {
            user.setFlag(0);
        }
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public RoleEX selectUserRoleById(int id) throws RuntimeException {
        return roleEXMapper.selectById(id);
    }


}
