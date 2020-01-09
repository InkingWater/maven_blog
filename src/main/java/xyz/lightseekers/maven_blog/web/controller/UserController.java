package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.lightseekers.maven_blog.bean.User;
import xyz.lightseekers.maven_blog.service.IUserService;
import xyz.lightseekers.maven_blog.util.MD5Util;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(description = "用户模块-翁承煜")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    @ApiOperation("登录校验")
    public Message login(String username, String password) {
        return MessageUtil.success(userService.login(username, MD5Util.createPassword(password)));
    }

    @PostMapping("/changePassword")
    @ApiOperation("修改密码")
    public Message changePassword(int id, String oldPassword, String newPassword) {
        return MessageUtil.success(userService.changePassword(id, MD5Util.createPassword(oldPassword), MD5Util.createPassword(newPassword)));
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public Message register(String username, String password, String email, String name) {
        return MessageUtil.success(userService.register(username, MD5Util.createPassword(password), email, name));
    }

    @GetMapping("/selectById")
    @ApiOperation("根据id查找一个用户信息")
    public Message selectById(int id) {
        return MessageUtil.success(userService.selectById(id));
    }

    @PostMapping("/insert")
    @ApiOperation("新增用户")
    public Message insert(User user, MultipartFile uploadFile, HttpServletRequest request) {
        user.setPassword(MD5Util.createPassword(user.getPassword()));
        return MessageUtil.success(userService.addOrUpdate(user,uploadFile,request));
    }

    @PostMapping("/update")
    @ApiOperation("修改用户信息")
    public Message update(User user) {
        user.setPassword(MD5Util.createPassword(user.getPassword()));
        return MessageUtil.success(userService.addOrUpdate(user,null,null));
    }

    @GetMapping("/deleteById")
    @ApiOperation("根据id删除用户")
    public Message deleteById(int id) {
        return MessageUtil.success(userService.deleteById(id));
    }

    @GetMapping("/selectAll")
    @ApiOperation("查询所有用户")
    public Message selectAll() {
        return MessageUtil.success(userService.selectAll());
    }

    @PostMapping("/assignRoles")
    @ApiOperation("为用户分配角色")
    public Message assignRoles(int userId, int roleId) {
        return MessageUtil.success(userService.assignRoles(userId, roleId));
    }

    @PostMapping("/openOrCloseUser")
    @ApiOperation("封号或恢复")
    public Message openOrCloseUser(int id, boolean choose) {
        return MessageUtil.success(userService.openOrCloseUser(id, choose));
    }

    @GetMapping("/selectUserRoleById")
    @ApiOperation("返回用户对应的角色和权限")
    public Message selectUserRoleById(int id) {
        return MessageUtil.success(userService.selectUserRoleById(id));
    }

    @GetMapping("/deleteMany")
    @ApiOperation("批量删除")
    public Message deleteMany(int id[]) {
        return MessageUtil.success(userService.deleteMany(id));
    }

    @GetMapping("/loginByEmail")
    @ApiOperation("以邮件登录")
    public Message loginByEmail(String to){
        return MessageUtil.success(userService.loginByEmail(to));
    }
}
