package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.User;
import xyz.lightseekers.maven_blog.bean.ex.RoleEX;
import xyz.lightseekers.maven_blog.bean.ex.TokenEx;

import java.util.List;

public interface IUserWService {

     TokenEx login(String username, String password) throws RuntimeException;
     int changePassword(int id,String oldPassword,String NewPassword) throws RuntimeException;
     int register(String username,String password,String email,String name) throws RuntimeException;
     User selectById(int id) throws RuntimeException;
     int deleteById(int id) throws RuntimeException;
     int addOrUpdate(User user) throws RuntimeException;
     List<User> selectAll() throws RuntimeException;
     int assignRoles(int userid,int roleid) throws RuntimeException;
     int openOrClossUser(int id,boolean choose) throws RuntimeException;
     RoleEX selectUserRoleById(int id) throws RuntimeException;


}
