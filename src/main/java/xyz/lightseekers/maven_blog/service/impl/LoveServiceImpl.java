package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.*;
import xyz.lightseekers.maven_blog.bean.ex.LoveEXM;
import xyz.lightseekers.maven_blog.mapper.LoveMapper;
import xyz.lightseekers.maven_blog.mapper.UserMapper;
import xyz.lightseekers.maven_blog.mapper.VisitorMapper;
import xyz.lightseekers.maven_blog.mapper.ex.LoveEXMMapper;
import xyz.lightseekers.maven_blog.mapper.ex.LoveEXMapper;
import xyz.lightseekers.maven_blog.service.ILoveService;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoveServiceImpl implements ILoveService {

    @Autowired
    private LoveEXMMapper loveEXMMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoveMapper loveMapper;


    /**
     * 产找所有的love对象
     * @return love的对象集合 包括对应id的user和blog对象
     * @throws RuntimeException
     */
    @Override
    public List<LoveEXM> selectAll() throws RuntimeException {
        return  loveEXMMapper.selectAll();
    }

    /**
     * 根据user的id查找对应的对象
     * @param id user的id
     * @return user对象
     * @throws RuntimeException
     */
    @Override
    public User selectById( int id ) throws RuntimeException {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 查找所有点赞了的用户
     * @return user集合
     * @throws RuntimeException
     */
    @Override
    public List<User> selectListById() throws RuntimeException {
        List<LoveEXM> loveEXMS = loveEXMMapper.selectAll();
        List<User> users = new ArrayList<>();
        for (LoveEXM loveEXM : loveEXMS) {
            User user = selectById(loveEXM.getUser().getId());
            user.setPassword(null);
            if(!users.contains(user)){
                users.add(user);
            }
        }
        return users;
    }

    /**
     * 根据博客查找给该博客点赞的user
     * @param id 博客的id
     * @return user集合
     * @throws RuntimeException
     */
    @Override
    public List<User> selectAllByBlog(int id) throws RuntimeException {
        List<Integer> list = loveEXMMapper.selectByBlog(id);
        List<User> list1 = new ArrayList<>();
        for (Integer integer : list) {
            User user = userMapper.selectByPrimaryKey(integer);
            user.setPassword(null);
            if(!list1.contains(user)){
                list1.add(user);
            }
        }
        return list1;
    }

    /**
     * 在love表中插入数据
     * @param love love
     * @return
     * @throws RuntimeException
     */
    @Override
    public int insert(Love love) throws RuntimeException {
        return loveMapper.insert(love);
    }

    /**
     * 通过id删除love中的记录
     * @param id love的id值
     * @return delete方法值
     * @throws RuntimeException
     */
    @Override
    public int deleteLoveById(int id) throws RuntimeException {
        return loveMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新love表中的数据
     * @param love  传入整个love对象
     * @return update
     * @throws RuntimeException
     */
    @Override
    public int update(Love love) throws RuntimeException {
        return loveMapper.updateByPrimaryKey(love);
    }

    /**
     * 通过id查找对应的love记录
     * @param id love的id
     * @return LoveEX对象 包括user blog子对象
     * @throws RuntimeException
     */
    @Override
    public List<LoveEXM> selectByID(int id) throws RuntimeException {
        return loveEXMMapper.selectById(id);
    }

    @Override
    public List<LoveEXM> selectAllLoveByBlog(int id) throws RuntimeException {
        return loveEXMMapper.selectAllByBlog(id);
    }
}
