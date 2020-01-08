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
@RequestMapping("visitor")
@Api(description = "访问管理")
public class VisitorController {

    @Autowired
    private VisitorServiceImpl visitorService;

    @GetMapping("/selectAllVisitor")
    @ApiOperation(value = "查找全部的visitor信息")
    public Message selectAllVisitor( ){
        return MessageUtil.success( visitorService.selectAllVisitor());
    }


    @GetMapping("/selectAllByUrl")
    @ApiOperation(value = "查找某一个url的访问记录")
    public Message selectAllByUrl(String url){
        return MessageUtil.success(visitorService.selectAllByURl(url));
    }

    @GetMapping("/selectAllByIp")
    @ApiOperation(value = "/查找指定ip的访问记录")
    public Message selectAllByIp(String ip){
        return MessageUtil.success(visitorService.selectAllByIp(ip));
    }


    @GetMapping("/deleteById")
    @ApiOperation(value = "删除记录")
    public Message deleteById(int id){
        return MessageUtil.success(visitorService.deleteById(id));
    }


    @PostMapping("/insert")
    @ApiOperation(value = "插入新的记录")
    public Message insert(HttpServletRequest request){
        Visitor visitor = new Visitor();
        setVisitor(request,visitor);
        return MessageUtil.success(visitorService.insert(visitor));
    }


    @PostMapping("/update")
    @ApiOperation(value = "更新新的数据")
    public Message update(Visitor visitor,HttpServletRequest request){
        return MessageUtil.success(visitorService.update(visitor));
    }

    @GetMapping("/deleteBatch")
    @ApiOperation(value = "进行批量删除")
    public Message deleteBatch(int[] ids){
        return MessageUtil.success(visitorService.deleteByBatch(ids));
    }

    public void setVisitor(HttpServletRequest request, Visitor visitor){
        String ip = BaiDuUtil.getIpAddr(request);
        visitor.setIp(ip);
        visitor.setUrl(request.getRequestURI());
        visitor.setLatitude((double)getLongitudeAndLatitude(ip).get("latitude"));
        visitor.setLongitude((double)getLongitudeAndLatitude(ip).get("longitude"));
        visitor.setAddress((String.valueOf(getLongitudeAndLatitude(ip).get("address"))));
        visitor.setDate(new Date());
    }


    @GetMapping("/download")
    public void download(HttpServletResponse response) throws Exception{
        List<Visitor> visitors = visitorService.selectAllVisitor();

       XSSFWorkbook workbook = new XSSFWorkbook();
       XSSFSheet sheet = workbook.createSheet("访问信息");

       //表头
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellType(CellType.STRING);
        cell.setCellValue("访问者的信息集合");

        //第二行
        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("visitor_id");
        row1.createCell(1).setCellValue("visitor_ip");
        row1.createCell(2).setCellValue("visitor_url");
        row1.createCell(3).setCellValue("visitor_longitude");
        row1.createCell(4).setCellValue("visitor_latitude");
        row1.createCell(5).setCellValue("visitor_address");
        row1.createCell(6).setCellValue("visitor_date");

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));

        //具体内容
        for(int i = 0; i < visitors.size(); i++ ){
            XSSFRow rowi = sheet.createRow( 2+i );
            rowi.createCell(0).setCellValue(visitors.get(i).getId());
            rowi.createCell(1).setCellValue(visitors.get(i).getIp());
            rowi.createCell(2).setCellValue(visitors.get(i).getUrl());
            rowi.createCell(3).setCellValue(visitors.get(i).getLongitude());
            rowi.createCell(4).setCellValue(visitors.get(i).getLatitude());
            rowi.createCell(5).setCellValue(visitors.get(i).getAddress());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            rowi.createCell(6).setCellValue(df.format(visitors.get(i).getDate()));
            System.out.println(visitors.get(i).getDate());
        }
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("里斯的表"+".xlsx", "utf-8"));
        workbook.write(response.getOutputStream());
    }
}
