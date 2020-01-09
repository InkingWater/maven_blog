package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.Authority;
import xyz.lightseekers.maven_blog.service.IAuthorityService;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

@RestController
@RequestMapping("/authority")
@Api(description = "权限模块-翁承煜")
public class AuthorityController {

    @Autowired
    private IAuthorityService authorityService;

    @PostMapping("/insert")
    @ApiOperation("增加权限")
    public Message insert(Authority authority) {
        return MessageUtil.success(authorityService.addOrUpdate(authority));
    }

    @PostMapping("/update")
    @ApiOperation("修改权限")
    public Message update(Authority authority) {
        return MessageUtil.success(authorityService.addOrUpdate(authority));
    }

    @GetMapping("/deleteById")
    @ApiOperation("删除权限")
    public Message deleteById(int id) {
        return MessageUtil.success(authorityService.deleteById(id));
    }

    @GetMapping("/selectAll")
    @ApiOperation("查询所有")
    public Message selectAll() {
        return MessageUtil.success(authorityService.selectAll());
    }

    @GetMapping("/selectByName")
    @ApiOperation("模糊查询")
    public Message selectByName(String word) {
        return MessageUtil.success(authorityService.selectByName(word));
    }

}
