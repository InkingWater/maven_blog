package xyz.lightseekers.maven_blog.mapper_test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.lightseekers.maven_blog.bean.AuthorityExample;
import xyz.lightseekers.maven_blog.mapper.AuthorityMapper;

/**
 * @Author: ZhiliangJia
 * @Description:
 * @Date:Create in 14:58 2019/12/31
 * @Modified User:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class AuthorityMapperTest {

    @Autowired
    private AuthorityMapper authorityMapper;

    @Test
    public void selectAll(){
        AuthorityExample authorityExample=new AuthorityExample();
        System.out.println(authorityMapper.selectByExample(authorityExample));
    }
}
