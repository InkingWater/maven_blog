package xyz.lightseekers.maven_blog.mapper.ex;

import xyz.lightseekers.maven_blog.bean.ex.LoveEXM;

import java.util.List;

public interface LoveEXMMapper {

    /**
     * 查看所有点赞人
     * @return  返回user对象集合
     * @throws RuntimeException
     */
    List<LoveEXM> selectAll() throws RuntimeException;


//    /**
//     * 查看love表中的
//     * @return
//     * @throws RuntimeException
//     */
//    List<User> selectAllInLove() throws RuntimeException;

    /**
     * 通过博客查找User对象的id
     * @param id 博客的id
     * @return 用户id的Integer集合
     * @throws RuntimeException
     */
    List<Integer> selectByBlog(int id) throws RuntimeException;

    /**
     * 通过id查找love对象
     * @param id love的id
     * @return LoveEX的对象集合
     * @throws RuntimeException
     */
    List<LoveEXM> selectById(int id) throws RuntimeException;


    /**
     * 通过blogid查找点赞记录 包括user对象
     * @param id
     * @return
     * @throws RuntimeException
     */
    List<LoveEXM> selectAllByBlog(int id) throws RuntimeException;
}
