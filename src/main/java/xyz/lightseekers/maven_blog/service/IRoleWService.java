package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Role;
import xyz.lightseekers.maven_blog.bean.ex.RoleEX;

import java.util.List;

public interface IRoleWService {
    int insertRole(Role role) throws RuntimeException;
    int deleteRoleById(int id) throws RuntimeException;
    List<RoleEX> selectAll() throws RuntimeException;
    int insertRoleAuth(int authid,int parentid) throws RuntimeException;
}
