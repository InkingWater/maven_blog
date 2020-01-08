package xyz.lightseekers.maven_blog.mapper_test.ex;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.lightseekers.maven_blog.mapper.ex.RoleEXMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class RoleEXMapperTest {

    @Autowired
    private RoleEXMapper roleEXMapper;

    @Test
    public void selectById() {
        System.out.println(roleEXMapper.selectById(1));
    }
}
