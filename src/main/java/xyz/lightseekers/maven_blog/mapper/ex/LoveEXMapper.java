package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.ex.LoveDataExcelEX;
import xyz.lightseekers.maven_blog.bean.ex.LoveEX;
import xyz.lightseekers.maven_blog.bean.ex.LoveUserBlogEX;

import java.util.List;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/2 15:06
 */
@Repository
public interface LoveEXMapper {

    /**
     * 设置Excel
     *
     * @return
     */
    List<LoveDataExcelEX> selectAllUserExcel();

    /**
     * 设置Excel
     *
     * @param id
     * @return
     */
    List<LoveDataExcelEX> selectLoveExcelByBlogId(int id);

    /**
     * 查看所有点赞人
     *
     * @return 返回user对象集合
     * @throws RuntimeException
     */
    List<LoveEX> selectAllUser() throws RuntimeException;

    /**
     * 通过博客查找User对象的id
     *
     * @param id 博客的id
     * @return 用户id的Integer集合
     * @throws RuntimeException
     */
    List<Integer> selectByBlog(int id) throws RuntimeException;

    /**
     * 通过id查找love对象
     *
     * @param id love的id
     * @return LoveEX的对象集合
     * @throws RuntimeException
     */
    List<LoveEX> selectById(int id) throws RuntimeException;


    /**
     * 通过blogid查找点赞记录 包括user对象
     *
     * @param id
     * @return
     * @throws RuntimeException
     */
    List<LoveEX> selectAllByBlog(int id) throws RuntimeException;
}
