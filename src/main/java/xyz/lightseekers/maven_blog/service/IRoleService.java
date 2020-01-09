package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Role;
import xyz.lightseekers.maven_blog.bean.ex.RoleEX;

import java.util.List;

public interface IRoleService {
    int insertRole(Role role) throws RuntimeException;

    int deleteRoleById(int id) throws RuntimeException;

    List<RoleEX> selectAll() throws RuntimeException;

    int insertRoleAuth(int authId, int parentId) throws RuntimeException;
}
