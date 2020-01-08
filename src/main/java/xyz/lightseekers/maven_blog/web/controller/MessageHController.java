package xyz.lightseekers.maven_blog.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.lightseekers.maven_blog.bean.ex.MessageEX;
import xyz.lightseekers.maven_blog.service.IMessageHService;
import xyz.lightseekers.maven_blog.util.BaiDuUtil;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/message")
@Api(description = "留言板")

public class MessageHController {
    @Autowired
    private IMessageHService messageHService;

    @PostMapping("/insertMessage")
    @ApiOperation("添加留言")
    public Message insertMessage(HttpServletRequest request, xyz.lightseekers.maven_blog.bean.Message message)
    {
        String ipAddr = BaiDuUtil.getIpAddr(request);
        Map<String,Object> json= BaiDuUtil.getLongitudeAndLatitude(ipAddr);
        message.setIp(ipAddr);
        message.setLatitude(Double.parseDouble((json.get("latitude")).toString()));
        message.setLongitude(Double.parseDouble((json.get("longitude").toString())));
        return MessageUtil.success(messageHService.insertMessage(message));
    }

    @GetMapping("/deleteMessage")
    @ApiOperation("删除留言")
    public Message deleteMessage(int id)
    {
        return MessageUtil.success(messageHService.deleteMessage(id));
    }

    @GetMapping("/selectMessage")
    @ApiOperation("查找留言")
    public  Message selectMessage(String name)
    {
        List<MessageEX> list= messageHService.selectByName(name);
        return MessageUtil.success(list);
    }

    @GetMapping("/deleteMessageP")
    @ApiOperation("批量删除留言")
    public Message deleteMessageP(int[] id)
    {
        return MessageUtil.success(messageHService.deleteMessageP(id));
    }

    @GetMapping("/downloadMessage")
    @ApiOperation("下载")
    public void downloadMessage(HttpServletResponse response) throws IOException {
        //String filePathStr = "D:";
        //String fileNameStr = "Messagep.xls";
        //File filePath = new File(filePathStr);
        //File file = new File(filePathStr + "\\" + fileNameStr);
        //if (!filePath.isDirectory()) {
        //    filePath.mkdirs();
       // }
       // if (!file.exists()) {
       //     file.createNewFile();
       // } else {
        //    System.out.println("该文件已存在，请先删除后再生成！");
         //   return;
        //}
            // 1.创建工作簿
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFCellStyle style = workbook.createCellStyle();


            // 2. 创建工作表格
            XSSFSheet sheet = workbook.createSheet("留言");

            // 3.创建行
            XSSFRow row = sheet.createRow(0);
            // 4.创建单元格
            XSSFCell cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue("ID");
            cell = row.createCell(1);
            cell.setCellStyle(style);
            cell.setCellValue("留言");
            cell = row.createCell(2);
            cell.setCellStyle(style);
            cell.setCellValue("QQ");
            cell = row.createCell(3);
            cell.setCellStyle(style);
            cell.setCellValue("手机");
            cell = row.createCell(4);
            cell.setCellFormula("1+1");
            List<MessageEX> messages = messageHService.selectAll();
            for (int i = 0; i < messages.size(); i++) {
                XSSFRow row_i = sheet.createRow(i + 1);
                MessageEX message = messages.get(i);
                row_i.createCell(0).setCellValue(message.getId());
                row_i.createCell(1).setCellValue(message.getContent());
                row_i.createCell(2).setCellValue(message.getQq());
                row_i.createCell(3).setCellValue(message.getTel());
            }
            // 5.设置单元格数据类型
            cell.setCellType(CellType.STRING);
            //6.设置单元格内容
          //  cell.setCellValue("留言");

        /*
         *      / 告诉浏览器用什么软件可以打开此文件
                 response.setHeader("content-Type", "application/vnd.ms-excel");
                   // 下载文件的默认名称
                   response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(excelName+".xlsx", "utf-8"));

         */
      //  FileOutputStream fileOut = new FileOutputStream(file);
       // workbook.write(fileOut);
       // fileOut.close();
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("留言.xlsx", "utf-8"));
            workbook.write(response.getOutputStream());


        }

}
