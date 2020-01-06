package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.Authority;
import xyz.lightseekers.maven_blog.service.IAuthorityWService;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

@RestController
@RequestMapping("/authority")
@Api(description = "权限管理")
public class AuthorityWController {

    @Autowired
    private IAuthorityWService authorityWService;

    @PostMapping("/add")
    @ApiOperation("增加权限")
    public Message add(Authority authority){
        return MessageUtil.success(authorityWService.addOrUpdate(authority));
    }
    @PostMapping("/update")
    @ApiOperation("修改权限")
    public Message update(Authority authority){
        return MessageUtil.success(authorityWService.addOrUpdate(authority));
    }

    @GetMapping("/deleteById")
    @ApiOperation("删除权限")
    public Message deleteById(int id){
        return MessageUtil.success(authorityWService.deleteById(id));
    }

    @GetMapping("/selectAll")
    @ApiOperation("查询所有")
    public Message selectAll(){
        return MessageUtil.success(authorityWService.selectAll());
    }

    @GetMapping("/search")
    @ApiOperation("模糊查询")
    public Message search(String word){
        return MessageUtil.success(authorityWService.search(word));
    }

}
