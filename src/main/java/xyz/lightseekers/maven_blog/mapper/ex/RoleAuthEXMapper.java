package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.ex.RoleAuthEX;

@Repository
public interface RoleAuthEXMapper {
    RoleAuthEX selectById(int id);
}
