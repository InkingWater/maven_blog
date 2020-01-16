package xyz.lightseekers.maven_blog.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import xyz.lightseekers.maven_blog.bean.Love;
import xyz.lightseekers.maven_blog.bean.ex.LoveDataExcelEX;
import xyz.lightseekers.maven_blog.util.BaiDuUtil;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.bean.ex.LoveEX;
import xyz.lightseekers.maven_blog.service.impl.LoveServiceImpl;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/love")
@Api(description = "点赞模块-马嘉星")
public class LoveController {

    @Autowired
    private LoveServiceImpl loveService;

    @GetMapping("/selectAllLove")
    @ApiOperation(value = "查找全部love表中的数据")
    public Message selectAllLove() {
        List<LoveEX> loveEXMS = loveService.selectAll();
        return MessageUtil.success(loveEXMS);
    }

    @GetMapping("/selectAllUser")
    @ApiOperation(value = "查找love表中的所有user（无重复）")
    public Message selectAllUser() {
        return MessageUtil.success(loveService.selectListById());
    }

    @GetMapping("/selectUserByBlog")
    @ApiOperation(value = "根据博文查找点赞的用户有哪些（只用户无其他 无重复）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "博客的id", paramType = "query", dataType = "int", required = true),
    })
    public Message selectUserByBlog(int id) {
        return MessageUtil.success(loveService.selectAllByBlog(id));
    }


    @GetMapping("/selectLoveById")
    @ApiImplicitParam(name = "id", value = "点赞记录的id", paramType = "query", dataType = "int", required = true)
    @ApiOperation(value = "根据id查找")
    public Message selectLoveById(int id) {
        return MessageUtil.success(loveService.selectByID(id));
    }


    @GetMapping("/selectLoveByBlog")
    @ApiOperation(value = "根据blog_id查找，返回loveEXM集合")
    @ApiImplicitParam(name = "id", value = "博客的id", paramType = "query", dataType = "int", required = true)
    public Message selectLoveByBlog(int id) {
        return MessageUtil.success(loveService.selectAllByBlog(id));
    }

    @GetMapping("/deleteLoveById")
    @ApiOperation(value = "根据id删除")
    public Message deleteLoveById(int id, HttpServletRequest request) {
        return MessageUtil.success(loveService.deleteLoveById(id));
    }


    @PostMapping("/insertLove")
    @ApiOperation(value = "插入新的数据")
    public Message insertLove(Love love, HttpServletRequest request) {
        BaiDuUtil.setLove(request, love);
        return MessageUtil.success(loveService.insert(love));
    }


    @PostMapping("/updateLove")
    @ApiOperation(value = "更新新的数据")
    public Message updateLove(Love love, HttpServletRequest request) {
        return MessageUtil.success(loveService.update(love));
    }

    @GetMapping("/deleteBatch")
    @ApiOperation(value = "传入id数组进行批量删除")
    public Message deleteBatch(int[] ids) {
        return MessageUtil.success(loveService.deleteBatch(ids));
    }


    @GetMapping("/downloadDatabaseByBlog")
    public void downloadDatabaseByBlog(int id, HttpServletResponse response) throws Exception {
        loveService.downloadDatabaseByBlog(response, id);
    }
}
