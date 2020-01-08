package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.User;
import xyz.lightseekers.maven_blog.service.IUserWService;
import xyz.lightseekers.maven_blog.util.MD5Util;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

@RestController
@Api(description = "用户管理")
@RequestMapping("/user")
public class UserWController {

    @Autowired
    private IUserWService userService;

    @GetMapping("/login")
    @ApiOperation("登录校验")
    public Message login(String usernamer, String password){
        return MessageUtil.success(userService.login(usernamer,MD5Util.createPassword(password)));
    }

    @PostMapping("/changePassword")
    @ApiOperation("修改密码")
    public Message changePassword(int id,String oldPassword,String newPassword){
        return MessageUtil.success(userService.changePassword(id, MD5Util.createPassword(oldPassword), MD5Util.createPassword(newPassword)));
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public Message register(String username,String password,String email,String name){
        return MessageUtil.success(userService.register(username, MD5Util.createPassword(password), email, name));
    }

    @GetMapping("/selectById")
    @ApiOperation("根据id查找一个用户信息")
    public Message selectById(int id){
        return MessageUtil.success(userService.selectById(id));
    }

    @PostMapping("/add")
    @ApiOperation("新增用户")
    public Message add(User user){
        user.setPassword(MD5Util.createPassword(user.getPassword()));
        return MessageUtil.success(userService.addOrUpdate(user));
    }

    @PostMapping("/update")
    @ApiOperation("修改用户信息")
    public Message update(User user){
        user.setPassword(MD5Util.createPassword(user.getPassword()));
        return MessageUtil.success(userService.addOrUpdate(user));
    }

    @GetMapping("/deleteById")
    @ApiOperation("根据id删除用户")
    public Message deleteById(int id){
        return MessageUtil.success(userService.deleteById(id));
    }

    @GetMapping("/selectAll")
    @ApiOperation("查询所有用户")
    public Message selectAll(){
        return MessageUtil.success(userService.selectAll());
    }

    @PostMapping("/assignRoles")
    @ApiOperation("为用户分配角色")
    public Message assignRoles(int userid,int roleid){
        return MessageUtil.success(userService.assignRoles(userid, roleid));
    }

    @PostMapping("/openOrClossUser")
    @ApiOperation("封号或恢复")
    public Message openOrClossUser(int id,boolean choose){
        return MessageUtil.success(userService.openOrClossUser(id, choose));
    }

    @GetMapping("/selectUserRoleById")
    @ApiOperation("返回用户对应的角色和权限")
    public Message selectUserRoleById(int id){
        return MessageUtil.success(userService.selectUserRoleById(id));
    }

    @GetMapping("/deleteMany")
    @ApiOperation("批量删除")
    public Message deleteMany(int id[]){ return  MessageUtil.success(userService.deleteMany(id));}

}
