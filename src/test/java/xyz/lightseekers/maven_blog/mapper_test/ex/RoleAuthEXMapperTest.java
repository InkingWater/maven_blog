package xyz.lightseekers.maven_blog.mapper_test.ex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.lightseekers.maven_blog.mapper.ex.RoleAuthEXMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleAuthEXMapperTest {

    @Autowired
    private RoleAuthEXMapper roleAuthEXMapper;

    @Test
    public void selectById(){
        System.out.println(roleAuthEXMapper.selectById(1));
    }
}
