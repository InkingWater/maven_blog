package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Role;
import xyz.lightseekers.maven_blog.bean.RoleAuth;
import xyz.lightseekers.maven_blog.bean.ex.RoleEX;
import xyz.lightseekers.maven_blog.mapper.RoleAuthMapper;
import xyz.lightseekers.maven_blog.mapper.RoleMapper;
import xyz.lightseekers.maven_blog.mapper.ex.RoleAuthEXMapper;
import xyz.lightseekers.maven_blog.mapper.ex.RoleEXMapper;
import xyz.lightseekers.maven_blog.service.IRoleWService;

import java.util.List;

@Service
public class RoleWServiceImpl implements IRoleWService {
    @Autowired
    private RoleEXMapper roleEXMapper;
    @Autowired
    private RoleAuthEXMapper authEXMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleAuthMapper roleAuthMapper;

    /**
     *插入角色
     * @param role 角色
     * @return 常规值
     * @throws RuntimeException
     */
    @Override
    public int insertRole(Role role) throws RuntimeException {
        RoleAuth roleAuth = new RoleAuth();
        roleAuth.setAuthId(1);
        authEXMapper.insertInit(roleAuth);
        role.setAuthId(roleAuth.getId());
        return roleEXMapper.insertRole(role);
    }

    /**
     *删除角色
     * @param id 要删除的角色id
     * @return 删除角色数
     * @throws RuntimeException
     */
    @Override
    public int deleteRoleById(int id) throws RuntimeException {
        Role role = roleMapper.selectByPrimaryKey(id);
        int authid=role.getAuthId();
        roleMapper.deleteByPrimaryKey(id);
        roleAuthMapper.deleteByPrimaryKey(authid);
        return 1;
    }

    /**
     * 查询所有角色
     * @return 所有角色的集合
     * @throws RuntimeException
     */
    @Override
    public List<RoleEX> selectAll() throws RuntimeException {
        return roleEXMapper.selectAll();
    }

    /**
     * 分配新的权限
     * @param authid 权限id
     * @param parentid  桥表中父权限id
     * @return
     * @throws RuntimeException
     */
    @Override
    public int insertRoleAuth(int authid, int parentid) throws RuntimeException {
        return authEXMapper.inserRoleAhth(authid, parentid);
    }


}
