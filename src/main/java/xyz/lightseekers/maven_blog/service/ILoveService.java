package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Love;
import xyz.lightseekers.maven_blog.bean.User;
import xyz.lightseekers.maven_blog.bean.ex.LoveEX;
import xyz.lightseekers.maven_blog.bean.ex.LoveDataExcelEX;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ILoveService {

    List<LoveEX> selectAll() throws RuntimeException;

    User selectById(int id) throws RuntimeException;

    List<User> selectListById() throws RuntimeException;

    List<User> selectAllByBlog(int id) throws RuntimeException;

    int insert(Love love) throws RuntimeException;

    int deleteLoveById(int id) throws RuntimeException;

    int update(Love love) throws RuntimeException;

    List<LoveEX> selectByID(int id) throws RuntimeException;

    List<LoveEX> selectAllLoveByBlog(int id) throws RuntimeException;

    int deleteBatch(int[] ids) throws RuntimeException;

    List<LoveDataExcelEX> selectAllExcel() throws RuntimeException;

    List<LoveDataExcelEX> selectLoveExcelByBlogId(int id) throws RuntimeException;

    void downloadDatabaseByBlog(HttpServletResponse response, int id) throws RuntimeException;
}
