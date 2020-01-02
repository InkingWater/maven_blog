package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.ex.RoleEX;

@Repository
public interface RoleEXMapper {
    RoleEX selectById(int id);
}
