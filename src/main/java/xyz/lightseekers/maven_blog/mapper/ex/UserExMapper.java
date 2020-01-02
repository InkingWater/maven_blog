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
    UserEX selectByUsernameAndPassword(String username,String password);
}
