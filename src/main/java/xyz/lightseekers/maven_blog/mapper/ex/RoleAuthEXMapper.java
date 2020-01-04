package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.ex.RoleAuthEX;

@Repository
public interface RoleAuthEXMapper {
    /**
     *
     * @param id 根权限的id
     * @return 根权限以及其的子权限
     */
    RoleAuthEX selectById(int id);
}
