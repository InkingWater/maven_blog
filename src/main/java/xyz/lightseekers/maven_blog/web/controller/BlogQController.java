package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.Blog;
import xyz.lightseekers.maven_blog.bean.ex.BlogUserTypeEXQ;
import xyz.lightseekers.maven_blog.bean.ex.BlogUserTypeWithoutArticleEXQ;
import xyz.lightseekers.maven_blog.service.IBlogQService;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;
import xyz.lightseekers.maven_blog.util.ZipUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("BlogQController")
@Api(description = "博客管理")
public class BlogQController {

    @Autowired
    private IBlogQService blogService;

    private List<File> files=new ArrayList<>();

    @GetMapping("/selectAll")
    @ApiOperation(value = "查询全部博客信息")
    public Message selectAll() {
        List<BlogUserTypeEXQ> list = blogService.selectAll();
        return MessageUtil.success(list);
    }

    @GetMapping("/selectAllWithoutArticle")
    @ApiOperation(value = "查询除博客内容之外的全部博客信息")
    public Message selectAllWithoutArticle() {
        List<BlogUserTypeWithoutArticleEXQ> list = blogService.selectAllWithoutArticle();
        return MessageUtil.success(list);
    }

    @GetMapping("/selectById")
    @ApiOperation(value = "通过Id查询全部博客信息")
    public Message selectById(int id) {
        return MessageUtil.success(blogService.selectById(id));
    }

    @PostMapping("/insert")
    @ApiOperation(value = "创建一条新博客")
    public Message insert(Blog blog) {
        return MessageUtil.success(blogService.insert(blog));
    }

    @GetMapping("/searchByBlogTitle")
    @ApiOperation(value = "通过博客标题模糊查询博客的全部信息")
    public Message searchByBlogTitle(String titleWord) {
        return MessageUtil.success(blogService.searchByBlogTitle(titleWord));
    }

    @GetMapping("/deleteById")
    @ApiOperation(value = "通过Id删除某一条博客")
    public Message deleteById(int id) {
        return MessageUtil.success(blogService.deleteById(id));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新某一条博客的内容，同时更新时间戳")
    public Message update(Blog blog) {
        return MessageUtil.success(blogService.update(blog));
    }

    @GetMapping("/selectBetweenDate")
    @ApiOperation(value = "查询某一段时间内的所有博客信息")
    public Message selectBetweenDate(Date lastDate, Date nextDate) {
        return MessageUtil.success(blogService.selectBetweenDate(lastDate, nextDate));
    }

    @GetMapping("/selectByTypeId")
    @ApiOperation(value = "查询某一栏目的全部博客信息")
    public Message selectByTypeId(int typeId){
        return MessageUtil.success(blogService.selectByTypeId(typeId));
    }

    @GetMapping("/selectToday")
    public Message selectToday(){
        return MessageUtil.success(blogService.selectToday());
    }

    @GetMapping("/selectDayCountByMonth")
    public Message selectDayCountByMonth(){
        return MessageUtil.success(blogService.selectDayCountByMonth());
    }
    @GetMapping("/deleteByBatch")
    @ApiOperation(value = "批量删除")
    public Message deleteByBatch(int ids[]) {
        return MessageUtil.success(blogService.deleteByBatch(ids));
    }

    @GetMapping("/selectByVisitor")
    @ApiOperation(value = "查询访客量最多的十条数据")
    public Message selectByVisitor(){
        return MessageUtil.success(blogService.selectByVisitor());
    }

    @GetMapping("/downloadBlog")
    @ApiOperation(value = "下载博客信息压缩包")
    public void download(HttpServletResponse response) throws IOException {
        files.clear();;
        List<BlogUserTypeEXQ> list = blogService.selectAll();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("博客内容");
//        第一行
        XSSFRow row1 = sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
        row1.createCell(0).setCellValue("博客信息");
        //第二行
        XSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("id");
        row2.createCell(1).setCellValue("username");
        row2.createCell(2).setCellValue("title");
        row2.createCell(3).setCellValue("article");
        row2.createCell(4).setCellValue("date");
        row2.createCell(5).setCellValue("love");
        row2.createCell(6).setCellValue("visitor");
        row2.createCell(7).setCellValue("typename");

        for (int i = 0; i < list.size(); i++) {
            XSSFRow row = sheet.createRow(i + 2);
            row.createCell(0).setCellValue(list.get(i).getId());
            row.createCell(1).setCellValue(list.get(i).getUser());
            row.createCell(2).setCellValue(list.get(i).getTitle());
            row.createCell(3).setCellValue("见" + list.get(i).getTitle() + ".TXT");
            row.createCell(4).setCellValue(list.get(i).getDate());
            row.createCell(5).setCellValue(list.get(i).getLove());
            row.createCell(6).setCellValue(list.get(i).getVisitor());
            row.createCell(7).setCellValue(list.get(i).getType());
            files.add(writeTXT(list.get(i).getTitle(), list.get(i).getArticle()));
        }
//
//        OutputStream os = response.getOutputStream();
        File writeName1 = new File("src/main/resources/static/blog/");
        FileOutputStream outputStream = new FileOutputStream(writeName1.getAbsoluteFile() + "\\" + "全部博客信息.xlsx");
        workbook.write(outputStream);
        outputStream.close();
        files.add(new File(writeName1.getAbsoluteFile() + "\\" + "全部博客信息.xlsx"));


        response.setHeader("content-Type", "application/zip");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("全部博客信息.zip", "utf-8"));
        ZipUtils.toZip(files, response.getOutputStream());

        files.forEach(file -> {
            file.delete();
        });
    }


    public static File writeTXT(String title, String content) {
        File writeName=null;
        try {
            // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
            /* 写入Txt文件 */
            writeName = new File("src/main/resources/static/blog/");// 相对路径，如果没有则要建立一个新的output。txt文件
            if (!writeName.exists()) {
                writeName.mkdirs();
            }
            System.out.println(writeName.getAbsolutePath());
            writeName = new File(writeName.getAbsolutePath() + "\\" + title + ".txt");// 相对路径，如果没有则要建立一个新的output。txt文件
            writeName.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writeName));
            out.write(content); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            System.out.println("执行成功");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return writeName;
        }
    }

}
