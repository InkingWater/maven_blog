package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.ex.CommentEX;

import java.util.List;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/2 14:34
 */
@Repository
public interface CommentEXMapper {
    List<CommentEX> selectByBlogId(int blogId);
}
