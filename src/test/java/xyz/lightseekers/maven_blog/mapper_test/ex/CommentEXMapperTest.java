package xyz.lightseekers.maven_blog.mapper_test.ex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.lightseekers.maven_blog.bean.ex.CommentEX;
import xyz.lightseekers.maven_blog.mapper.ex.CommentEXMapper;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/2 14:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentEXMapperTest {
    @Autowired
    private CommentEXMapper commentEXMapper;

    @Test
    public void selectByBlogId(){
        System.out.println(commentEXMapper.selectByBlogId(1));
    }
}
