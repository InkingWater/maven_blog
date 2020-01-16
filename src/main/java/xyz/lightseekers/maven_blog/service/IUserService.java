package xyz.lightseekers.maven_blog.service;

import org.springframework.web.multipart.MultipartFile;
import xyz.lightseekers.maven_blog.bean.User;
import xyz.lightseekers.maven_blog.bean.ex.RoleEX;
import xyz.lightseekers.maven_blog.bean.ex.TokenEX;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {

    TokenEX login(String username, String password) throws RuntimeException;

    int changePassword(int id, String oldPassword, String NewPassword) throws RuntimeException;

    int register(String username, String password, String email, String name) throws RuntimeException;

    User selectById(int id) throws RuntimeException;

    int deleteById(int id) throws RuntimeException;

    int deleteMany(int id[]) throws RuntimeException;

    int addOrUpdate(User user, MultipartFile uploadFile, HttpServletRequest request) throws RuntimeException;

    List<User> selectAll() throws RuntimeException;

    int assignRoles(int userId, int roleId) throws RuntimeException;

    int openOrCloseUser(int id, boolean choose) throws RuntimeException;

    RoleEX selectUserRoleById(int id) throws RuntimeException;

    String loginByEmail(String to) throws RuntimeException;

    String loginFace(MultipartFile uploadFile,HttpServletRequest request,Integer id);
}
