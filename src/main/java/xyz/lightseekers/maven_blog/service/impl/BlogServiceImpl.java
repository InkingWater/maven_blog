package xyz.lightseekers.maven_blog.service.impl;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Blog;
import xyz.lightseekers.maven_blog.bean.ex.BlogCountEX;
import xyz.lightseekers.maven_blog.bean.ex.BlogUserTypeEX;
import xyz.lightseekers.maven_blog.bean.ex.BlogWithoutArticleEX;
import xyz.lightseekers.maven_blog.mapper.BlogMapper;
import xyz.lightseekers.maven_blog.mapper.ex.BlogEXMapper;
import xyz.lightseekers.maven_blog.service.IBlogService;
import xyz.lightseekers.maven_blog.util.TxtUtil;
import xyz.lightseekers.maven_blog.util.ZipUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {

    @Autowired
    private BlogEXMapper blogEXMapper;

    @Autowired
    private BlogMapper blogMapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<File> files = new ArrayList<>();

    @Override
    public int deleteById(int id) throws RuntimeException {
        return blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Blog blog) throws RuntimeException {
        blog.setDate(new Date());
        return blogMapper.updateByPrimaryKey(blog);
    }

    @Override
    public List<BlogUserTypeEX> searchByBlogTitle(String titleWord) throws RuntimeException {
        titleWord = "%" + titleWord + "%";
        return blogEXMapper.selectByBlogTitle(titleWord);
    }

    @Override
    public int insert(Blog blog) throws RuntimeException {
        blog.setDate(new Date());
        return blogEXMapper.insert(blog);
    }

    @Override
    public List<BlogUserTypeEX> selectAll() throws RuntimeException {
        return blogEXMapper.selectAll();
    }

    @Override
    public List<BlogWithoutArticleEX> selectAllWithoutArticle() throws RuntimeException {
        return blogEXMapper.selectAllWithoutArticle();
    }

    @Override
    public BlogUserTypeEX selectById(int id) throws RuntimeException {
        return blogEXMapper.selectById(id);
    }

    @Override
    public List<BlogUserTypeEX> selectBetweenDate(Date lastDate, Date nextDate) throws RuntimeException {
        return blogEXMapper.selectBetweenDate(lastDate, nextDate);
    }


    @Override
    public List<BlogWithoutArticleEX> selectByTypeId(int typeId) throws RuntimeException {
        return blogEXMapper.selectByTypeId(typeId);


    }

    @Override
    public List<BlogUserTypeEX> selectToday() throws RuntimeException {
        return blogEXMapper.selectToday();
    }

    @Override
    public List<BlogCountEX> selectDayCountByMonth() throws RuntimeException {
        Calendar calendar_30 = Calendar.getInstance();
        calendar_30.set(calendar_30.get(Calendar.YEAR), calendar_30.get(Calendar.MONTH), calendar_30.get(Calendar.DAY_OF_MONTH) + 1 - 30, 0, 0, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) + 1, 0, 0, 0);
        return blogEXMapper.selectDayCountByMonth(calendar_30.getTime(), calendar.getTime());
    }

    @Override
    public int deleteByBatch(int[] ids) throws RuntimeException {
        for (int i =0;i< ids.length;i++){
            blogMapper.deleteByPrimaryKey(ids[i]);
        }
        return ids.length;
    }

    @Override
    public List<BlogWithoutArticleEX> selectByVisitor() throws RuntimeException {
        return blogEXMapper.selectByVisitor();
    }

    @Override
    public void download(HttpServletResponse response) throws RuntimeException {
        files.clear();
        List<BlogUserTypeEX> list = blogEXMapper.selectAll();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("博客内容");
        //第一行
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
            files.add(TxtUtil.writeTXT(list.get(i).getTitle(), list.get(i).getArticle()));
        }
        File writeName1 = new File("src/main/resources/static/blog/");
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(writeName1.getAbsoluteFile() + "\\" + "全部博客信息.xlsx");
            workbook.write(outputStream);
            outputStream.close();
            files.add(new File(writeName1.getAbsoluteFile() + "\\" + "全部博客信息.xlsx"));
            response.setHeader("content-Type", "application/zip");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("全部博客信息.zip", "utf-8"));
            ZipUtils.toZip(files, response.getOutputStream());

            files.forEach(file -> {
                file.delete();
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
