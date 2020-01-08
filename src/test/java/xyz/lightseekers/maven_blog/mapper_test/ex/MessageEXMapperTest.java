package xyz.lightseekers.maven_blog.mapper_test.ex;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.lightseekers.maven_blog.mapper.ex.MessageEXMapper;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/2 16:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class MessageEXMapperTest {

    @Autowired
    private MessageEXMapper messageEXMapper;

    @Test
    public void selectAll() {
        System.out.println(messageEXMapper.selectAll());
    }
}
