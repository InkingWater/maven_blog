package xyz.lightseekers.maven_blog.mapper_test.ex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.lightseekers.maven_blog.bean.ex.AuthorityEX;
import xyz.lightseekers.maven_blog.mapper.ex.AuthorityEXMapper;

/**
 * @Author: ZhiliangJia
 * @Description:
 * @Date:Create in 15:17 2019/12/31
 * @Modified User:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityEXMapperTest {
    @Autowired
    private AuthorityEXMapper authorityEXMapper;

    @Test
    public void selectChildren() {
        AuthorityEX authorityEX = authorityEXMapper.selectChildren(1);
        System.out.println(authorityEX.toString());
    }
}
