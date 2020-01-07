package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Token;
import xyz.lightseekers.maven_blog.bean.TokenExample;
import xyz.lightseekers.maven_blog.bean.User;
import xyz.lightseekers.maven_blog.bean.UserExample;
import xyz.lightseekers.maven_blog.bean.ex.RoleEX;
import xyz.lightseekers.maven_blog.bean.ex.TokenEx;
import xyz.lightseekers.maven_blog.mapper.TokenMapper;
import xyz.lightseekers.maven_blog.mapper.UserMapper;
import xyz.lightseekers.maven_blog.mapper.ex.RoleEXMapper;
import xyz.lightseekers.maven_blog.mapper.ex.UserExMapper;
import xyz.lightseekers.maven_blog.service.IUserWService;
import xyz.lightseekers.maven_blog.util.TokenUtil;

import java.util.Date;
import java.util.List;

@Service
public class UserWServiceImpl implements IUserWService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleEXMapper roleEXMapper;

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private UserExMapper userExMapper;

    /**
     *      登录校验
     * @param username 前端获取的用户账号
     * @param password  用户输入的密码
     * @return 成功返回Token 失败返回null
     * @throws RuntimeException
     */
    @Override
    public TokenEx login(String username, String password) throws RuntimeException {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if(users.size()==0){
            return null;
        }else if(users.get(0).getPassword()==password||password.equals(users.get(0).getPassword())){
            TokenExample tokenExample = new TokenExample();
            tokenExample.createCriteria().andUserIdEqualTo(users.get(0).getId());
            List<Token> tokens = tokenMapper.selectByExample(tokenExample);
            RoleEX roleEX = roleEXMapper.selectById(users.get(0).getRoleId());
            TokenEx tokenEx = new TokenEx();
            if(tokens.size()==0){
                Token token = new Token();
                token.setToken(TokenUtil.createToken(username, String.valueOf(new Date().getTime())));
                token.setUserId(users.get(0).getId());
                token.setDate(new Date());
                tokenMapper.insert(token);
                tokenEx.setToken(token.getToken());
                tokenEx.setUserId(token.getUserId());
                tokenEx.setName(users.get(0).getName());
                tokenEx.setUserName(users.get(0).getUsername());
                tokenEx.setRoleId(roleEX.getId());
                tokenEx.setRoleName(roleEX.getName());
            }
            else{
                Token token = tokens.get(0);
                tokenEx.setToken(token.getToken());
                tokenEx.setUserId(token.getUserId());
                tokenEx.setName(users.get(0).getName());
                tokenEx.setUserName(users.get(0).getUsername());
                tokenEx.setRoleId(roleEX.getId());
                tokenEx.setRoleName(roleEX.getName());
            }
            return tokenEx;
        }else{
            return null;
        }
    }

    /**
     *      修改密码
     * @param id userid
     * @param oldPassword 旧密码
     * @param NewPassword 新密码
     * @return 1表示成功，0表示失败:旧密码输入错误
     * @throws RuntimeException
     */
    @Override
    public int changePassword(int id, String oldPassword, String NewPassword) throws RuntimeException {
        User user = userMapper.selectByPrimaryKey(id);
        if (user.getPassword().equals(oldPassword)){
            user.setPassword(NewPassword);
            userMapper.updateByPrimaryKey(user);
            return 1;
        }else{
            return 0;
        }

    }

    /**
     *  注册
     * @param username 用户账号 唯一*
     * @param password 用户密码
     * @param email 用户邮箱
     * @param name 用户昵称
     * @return 1注册成功 0注册失败
     * @throws RuntimeException
     */
    @Override
    public int register(String username, String password, String email, String name) throws RuntimeException {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if (users.size()==0){
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            user.setFlag(1);
            user.setRoleId(2);
            return  userMapper.insert(user);
        }else{
            return 0;
        }


    }

    /**
     * 根据id查找用户信息
     * @param id 用户id
     * @return user类
     * @throws RuntimeException
     */
    @Override
    public User selectById(int id) throws RuntimeException {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     *  根据id删除用户
     * @param id
     * @return
     * @throws RuntimeException
     */
    @Override
    public int deleteById(int id) throws RuntimeException {
        return userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 增加或修改用户信息
     * @param user user.id 由系统自增长
     * @return 操作条数 0则 新增失败：username重复
     * @throws RuntimeException
     */
    @Override
    public int addOrUpdate(User user) throws RuntimeException {
        if (user == null) throw new RuntimeException();
        if(user.getId()==null){
            UserExample example = new UserExample();
            example.createCriteria().andUsernameEqualTo(user.getUsername());
            List<User> users = userMapper.selectByExample(example);
            if (users.size()==0){
                user.setFlag(1);
                user.setRoleId(2);
                return userMapper.insert(user);
            }
                return 0;
        }else{
            User user1 = selectById(user.getId());
            user.setFlag(user1.getFlag());
            user.setRoleId(user1.getRoleId());
            return userMapper.updateByPrimaryKey(user);
        }


    }

    /**
     * 列出所有用户信息
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
     * @param userid 用户id
     * @param roleid  角色id
     * @return  规范值
     * @throws RuntimeException
     */
    @Override
    public int assignRoles(int userid, int roleid) throws RuntimeException {
        User user = userMapper.selectByPrimaryKey(userid);
        user.setRoleId(roleid);
        return userMapper.updateByPrimaryKey(user);
    }

    /**
     * 禁用或启用用户
     * @param id 用户id
     * @param choose true不禁用|false禁用
     * @return 常规值
     * @throws RuntimeException
     */
    @Override
    public int openOrClossUser(int id, boolean choose) throws RuntimeException {
        User user = userMapper.selectByPrimaryKey(id);
        if(choose==true)
        {
            user.setFlag(1);
        }else {
            user.setFlag(0);
        }
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public RoleEX selectUserRoleById(int id) throws RuntimeException {
        return  roleEXMapper.selectById(id);
    }


}
