package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.ex.UserEX;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/2 11:09
 */
@Repository
public interface UserExMapper {
    /**
     * 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    UserEX selectByUsernameAndPassword(String username,String password);
}
