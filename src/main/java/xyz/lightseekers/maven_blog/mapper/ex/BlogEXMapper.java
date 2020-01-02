package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.ex.BlogEX;

/**
 * @Author: ZhiliangJia
 * @Description:
 * @Date:Create in 15:50 2019/12/31
 * @Modified User:
 */
@Repository
public interface BlogEXMapper {
    BlogEX selectById(int id);

    BlogEX selectAllNoContent();
}
