package xyz.lightseekers.maven_blog.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.Visitor;
import xyz.lightseekers.maven_blog.service.impl.VisitorServiceImpl;
import xyz.lightseekers.maven_blog.util.BaiDuUtil;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static xyz.lightseekers.maven_blog.util.BaiDuUtil.getLongitudeAndLatitude;

@RestController
@RequestMapping("/visitor")
@Api(description = "访客模块-马嘉星")
public class VisitorController {

    @Autowired
    private VisitorServiceImpl visitorService;

    @GetMapping("/selectAllVisitor")
    @ApiOperation(value = "查找全部的visitor信息")
    public Message selectAllVisitor() {
        return MessageUtil.success(visitorService.selectAllVisitor());
    }


    @GetMapping("/selectAllByUrl")
    @ApiOperation(value = "查找某一个url的访问记录")
    public Message selectAllByUrl(String url) {
        return MessageUtil.success(visitorService.selectAllByURl(url));
    }

    @GetMapping("/selectAllByIp")
    @ApiOperation(value = "/查找指定ip的访问记录")
    public Message selectAllByIp(String ip) {
        return MessageUtil.success(visitorService.selectAllByIp(ip));
    }


    @GetMapping("/deleteById")
    @ApiOperation(value = "删除记录")
    public Message deleteById(int id) {
        return MessageUtil.success(visitorService.deleteById(id));
    }


    @PostMapping("/insert")
    @ApiOperation(value = "插入新的记录")
    public Message insert(HttpServletRequest request,Visitor visitor) {
        BaiDuUtil.setVisitor(request, visitor);
        return MessageUtil.success(visitorService.insert(visitor));
    }


    @PostMapping("/update")
    @ApiOperation(value = "更新新的数据")
    public Message update(Visitor visitor) {
        return MessageUtil.success(visitorService.update(visitor));
    }

    @GetMapping("/deleteBatch")
    @ApiOperation(value = "进行批量删除")
    public Message deleteBatch(int[] ids) {
        return MessageUtil.success(visitorService.deleteByBatch(ids));
    }

    @ApiOperation(value = "下载访客")
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        visitorService.download(response);
    }
}
