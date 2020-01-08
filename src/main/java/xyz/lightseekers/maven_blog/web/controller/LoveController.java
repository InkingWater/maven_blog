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
import xyz.lightseekers.maven_blog.bean.ex.LoveExcle;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.bean.ex.LoveEXM;
import xyz.lightseekers.maven_blog.service.impl.LoveServiceImpl;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.List;

import static xyz.lightseekers.maven_blog.util.BaiDuUtil.getLongitudeAndLatitude;

@RestController
@RequestMapping("love")
@Api(description = "点赞管理")
public class LoveController {

    @Autowired
    private LoveServiceImpl loveService;


    @GetMapping("/selectAllLove")
    @ApiOperation(value = "查找全部love表中的数据")
    public Message selectAllLove(){
        List<LoveEXM> loveEXMS = loveService.selectAll();
        return MessageUtil.success(loveEXMS);
    }

    @GetMapping("/selectAllUser")
    @ApiOperation(value = "查找love表中的所有user（无重复）")
    public Message selectAllUser(){
        return MessageUtil.success(loveService.selectListById());
    }

    @GetMapping("/selectUserByBlog")
    @ApiOperation(value = "根据博文查找点赞的用户有哪些（只用户无其他 无重复）")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",value = "博客的id",paramType = "query",dataType = "int",required = true),
    })
    public Message selectUserByBlog(int id){
        return MessageUtil.success(loveService.selectAllByBlog(id));
    }


    @GetMapping("/selectLoveById")
    @ApiImplicitParam(name = "id",value = "点赞记录的id",paramType = "query",dataType = "int",required = true)
    @ApiOperation(value = "根据id查找")
    public Message selectLoveById(int id){
        return MessageUtil.success(loveService.selectByID(id));
    }



    @GetMapping("/selectLoveByBlog")
    @ApiOperation(value = "根据blog_id查找，返回loveEXM集合")
    @ApiImplicitParam(name = "id",value = "博客的id",paramType = "query",dataType = "int",required = true)
    public Message selectLoveByBlog(int id){
        return MessageUtil.success(loveService.selectAllByBlog(id));
    }

    @GetMapping("/deleteLoveById")
    @ApiOperation(value = "根据id删除")
    public Message deleteLoveById(int id, HttpServletRequest request){
        System.out.println(request.getRequestURI());
        return MessageUtil.success(loveService.deleteLoveById(id));
    }


    @PostMapping("/insertLove")
    @ApiOperation(value = "插入新的数据")
    public Message insertLove(Love love, HttpServletRequest request){
        String ip = getIpAddr(request);
        love.setIp(ip);
        love.setLongitude((double)getLongitudeAndLatitude(ip).get("longitude"));
        love.setLatitude((double)getLongitudeAndLatitude(ip).get("latitude"));
        return MessageUtil.success(loveService.insert(love));
    }


    @PostMapping("/updateLove")
    @ApiOperation( value = "更新新的数据")
    public Message updateLove(Love love,HttpServletRequest request)
    {
//        String ip = getIpAddr(request);
//        love.setIp(getIpAddr(request));
//        love.setLongitude((double)getLongitudeAndLatitude(ip).get("longitude"));
//        love.setLatitude((double)getLongitudeAndLatitude(ip).get("latitude"));
        return MessageUtil.success(loveService.update(love));
    }

    @GetMapping("/deleteBatch")
    @ApiOperation(value = "传入id数组进行批量删除")
    public Message deleteBatch(int[] ids){
        return MessageUtil.success(loveService.deleteBatch(ids));
    }

    @GetMapping("/downloadDatabase")
    public void downloadDatabase(int id, HttpServletResponse response)throws Exception{

        List<LoveExcle> loveExcles = loveService.selectAllExcle();
        List<LoveExcle> loveExcles1 = loveService.selectLoveExcelByBlogId(id);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("全部点赞信息");
        //表头
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellType(CellType.STRING);
        cell.setCellValue("点赞信息集合");

        //第二行
        XSSFRow row1 =sheet.createRow(1);
        row1.createCell(0).setCellValue("点赞序号");
        row1.createCell(1).setCellValue("点赞用户id");
        row1.createCell(2).setCellValue("点赞用户名称");
        row1.createCell(3).setCellValue("博客id");
        row1.createCell(4).setCellValue("博客标题");
        row1.createCell(5).setCellValue("点赞ip");
        row1.createCell(6).setCellValue("点赞经度");
        row1.createCell(7).setCellValue("点赞纬度");
        row1.createCell(8).setCellValue("点赞flag");

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,8));
        //内容
        for(int i = 0; i < loveExcles.size(); i++){
            XSSFRow rowi = sheet.createRow(2+i);
            rowi.createCell(0).setCellValue(loveExcles.get(i).getId());
            rowi.createCell(1).setCellValue(loveExcles.get(i).getUserId());
            rowi.createCell(2).setCellValue(loveExcles.get(i).getuName());
            rowi.createCell(3).setCellValue(loveExcles.get(i).getBlogId());
            rowi.createCell(4).setCellValue(loveExcles.get(i).getbName());
            rowi.createCell(5).setCellValue(loveExcles.get(i).getIp());
            rowi.createCell(6).setCellValue(loveExcles.get(i).getLongitude());
            rowi.createCell(7).setCellValue(loveExcles.get(i).getLatitude());
            rowi.createCell(8).setCellValue(loveExcles.get(i).getFlag());
        }
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("点赞的表"+".xlsx", "utf-8"));
        workbook.write(response.getOutputStream());
    }

    @GetMapping("/downloadDatabaseByBlog")
    public void downloadDatabaseByBlog(int id, HttpServletResponse response)throws Exception{

       // List<LoveExcle> loveExcles = loveService.selectAllExcle();
        List<LoveExcle> loveExcles1 = loveService.selectLoveExcelByBlogId(id);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(loveExcles1.get(0).getbName()+"的点赞信息");
        //表头
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellType(CellType.STRING);
        cell.setCellValue("点赞信息集合");

        //第二行
        XSSFRow row1 =sheet.createRow(1);
        row1.createCell(0).setCellValue("点赞序号");
        row1.createCell(1).setCellValue("点赞用户id");
        row1.createCell(2).setCellValue("点赞用户名称");
        row1.createCell(3).setCellValue("点赞ip");
        row1.createCell(4).setCellValue("点赞经度");
        row1.createCell(5).setCellValue("点赞纬度");
        row1.createCell(6).setCellValue("点赞flag");

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,6));
        //内容
        for(int i = 0; i < loveExcles1.size(); i++){
            XSSFRow rowi = sheet.createRow(2+i);
            rowi.createCell(0).setCellValue(loveExcles1.get(i).getId());
            rowi.createCell(1).setCellValue(loveExcles1.get(i).getUserId());
            rowi.createCell(2).setCellValue(loveExcles1.get(i).getuName());
            rowi.createCell(3).setCellValue(loveExcles1.get(i).getIp());
            rowi.createCell(4).setCellValue(loveExcles1.get(i).getLongitude());
            rowi.createCell(5).setCellValue(loveExcles1.get(i).getLatitude());
            rowi.createCell(6).setCellValue(loveExcles1.get(i).getFlag());
        }
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("点赞的表"+".xlsx", "utf-8"));
        workbook.write(response.getOutputStream());
    }


    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }
}
