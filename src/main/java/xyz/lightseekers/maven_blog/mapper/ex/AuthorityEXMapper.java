package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.ex.AuthorityEX;

/**
 * @Author: ZhiliangJia
 * @Description:
 * @Date:Create in 15:02 2019/12/31
 * @Modified User:
 */
@Repository
public interface AuthorityEXMapper {
    AuthorityEX selectChildren(int id);
}
