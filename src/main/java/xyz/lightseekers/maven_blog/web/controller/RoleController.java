package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.lightseekers.maven_blog.bean.Role;
import xyz.lightseekers.maven_blog.service.IRoleService;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

@RestController
@RequestMapping("/role")
@Api(description = "角色模块-翁承煜")
public class RoleController {
    @Autowired
    private IRoleService roleWService;


    @PostMapping("/add")
    @ApiOperation("新增角色")
    public Message add(Role role) {
        return MessageUtil.success(roleWService.insertRole(role));
    }

    @GetMapping("/deleteRoleById")
    @ApiOperation("删除角色")
    public Message deleteRoleById(int id) {
        return MessageUtil.success(roleWService.deleteRoleById(id));
    }

    @GetMapping("/selectAll")
    @ApiOperation("查询所有角色")
    public Message selectAll() {
        return MessageUtil.success(roleWService.selectAll());
    }

    @GetMapping("/insertRoleAuth")
    @ApiOperation("分配权限")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "authid", value = "权限id", paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "parentId", value = "父权限id", paramType = "query", dataType = "int")
    })
    public Message insertRoleAuth(int authid, int parentId) {
        return MessageUtil.success(roleWService.insertRoleAuth(authid, parentId));
    }

}
