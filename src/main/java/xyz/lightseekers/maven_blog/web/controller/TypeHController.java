package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.Type;
import xyz.lightseekers.maven_blog.service.TypeHService;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import java.util.List;

@RestController
@RequestMapping("/blogtype")
@Api(description = "博客类别管理")
public class TypeHController {
    @Autowired
    private TypeHService typeHService;

    @GetMapping("/secectType")
    @ApiOperation("查找类别信息")
    public Message selectType()
    {
        List<Type> list = typeHService.findType();
        return MessageUtil.success(list);
    }

    @GetMapping("/deleteType")
    @ApiOperation("删除类别信息")
    public Message deleteType(int id)
    {
        typeHService.deleteByid(id);
        return MessageUtil.success();
    }

    @PostMapping("/addType")
    @ApiOperation("添加信息")
    public Message addType(Type type)
    {
        typeHService.saveOrUpdate(type);
        return MessageUtil.success();
    }

    @GetMapping("/selectByname")
    @ApiOperation("关键字搜索")
    public Message selectByname(String key)
    {
        List<Type> list = typeHService.MHfind(key);
        return MessageUtil.success(list);
    }


    @PostMapping("update")
    @ApiOperation("更新")
    public Message updateType(Type type)
    {

        typeHService.saveOrUpdate(type);
        return  MessageUtil.success();
    }



}
