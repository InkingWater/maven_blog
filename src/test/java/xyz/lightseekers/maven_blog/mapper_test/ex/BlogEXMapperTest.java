package xyz.lightseekers.maven_blog.mapper_test.ex;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.lightseekers.maven_blog.mapper.ex.BlogEXMapper;

/**
 * @Author: ZhiliangJia
 * @Description:
 * @Date:Create in 16:09 2019/12/31
 * @Modified User:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class BlogEXMapperTest {
    @Autowired
    private BlogEXMapper blogEXMapper;

    @Test
    public void selectById(){
        System.out.println(blogEXMapper.selectUserById(1));
    }

    @Test
    public void selectAllNoContent(){
        System.out.println(blogEXMapper.selectAllNoContent());
    }
}
