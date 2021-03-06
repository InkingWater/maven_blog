package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.Role;
import xyz.lightseekers.maven_blog.bean.ex.RoleEX;

import java.util.List;

@Repository
public interface RoleEXMapper {
    /**
     * 根据角色编号获取角色，以及其所有的权限
     *
     * @param id 角色编号
     * @return 角色，以及其所有的权限
     */
    RoleEX selectById(int id);

    List<RoleEX> selectAll();

    int insertRole(Role Role);
}
