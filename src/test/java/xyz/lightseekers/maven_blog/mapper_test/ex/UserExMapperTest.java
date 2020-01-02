package xyz.lightseekers.maven_blog.mapper_test.ex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.lightseekers.maven_blog.bean.ex.UserEX;
import xyz.lightseekers.maven_blog.mapper.ex.UserExMapper;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/2 11:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserExMapperTest {
    @Autowired
    private UserExMapper userExMapper;

    @Test
    public void selectByUsernameAndPassword(){
        System.out.println(userExMapper.selectByUsernameAndPassword("admin","admin"));
    }
}