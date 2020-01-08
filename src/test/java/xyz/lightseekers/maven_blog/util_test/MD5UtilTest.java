package xyz.lightseekers.maven_blog.util_test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.lightseekers.maven_blog.util.MD5Util;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/7 9:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class MD5UtilTest {
    @Test
    public void password(){
        System.out.println(MD5Util.createPassword("654321"));
    }
}
