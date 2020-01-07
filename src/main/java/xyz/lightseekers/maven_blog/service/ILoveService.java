package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Love;
import xyz.lightseekers.maven_blog.bean.User;
import xyz.lightseekers.maven_blog.bean.ex.LoveEXM;

import java.util.List;

public interface ILoveService {

    List<LoveEXM> selectAll() throws RuntimeException;
    User selectById(int id ) throws RuntimeException;
    List<User> selectListById() throws RuntimeException;
    List<User> selectAllByBlog(int id ) throws RuntimeException;
    int insert(Love love) throws RuntimeException;
    int deleteLoveById(int id) throws RuntimeException;
    int update(Love love) throws RuntimeException;
    List<LoveEXM> selectByID(int id) throws RuntimeException;
    List<LoveEXM> selectAllLoveByBlog(int id) throws RuntimeException;

}
