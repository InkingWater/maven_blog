package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.Type;
import xyz.lightseekers.maven_blog.service.ITypeService;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import java.util.List;

@RestController
@RequestMapping("/blogType")
@Api(description = "博客类别模块-何恺越")
public class TypeController {
    @Autowired
    private ITypeService typeHService;

    @GetMapping("/selectType")
    @ApiOperation("查找类别信息")
    public Message selectType() {
        List<Type> list = typeHService.selectAll();
        return MessageUtil.success(list);
    }

    @GetMapping("/deleteType")
    @ApiOperation("删除类别信息")
    public Message deleteType(int id) {
        return MessageUtil.success(typeHService.deleteById(id));
    }

    @PostMapping("/insertType")
    @ApiOperation("添加信息")
    public Message addType(Type type) {
        return MessageUtil.success(typeHService.saveOrUpdate(type));
    }

    @GetMapping("/selectByName")
    @ApiOperation("关键字搜索")
    public Message selectByName(String key) {
        List<Type> list = typeHService.selectByKey(key);
        return MessageUtil.success(list);
    }


    @PostMapping("/update")
    @ApiOperation("更新")
    public Message updateType(Type type) {
        return MessageUtil.success(typeHService.saveOrUpdate(type));
    }

    @GetMapping("/deleteTypeP")
    @ApiOperation("批量删除类别")
    public Message deleteTypeP(int[] id) {
        return MessageUtil.success(typeHService.deleteTypes(id));
    }
}
