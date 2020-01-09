package xyz.lightseekers.maven_blog.service.impl;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Visitor;
import xyz.lightseekers.maven_blog.bean.VisitorExample;
import xyz.lightseekers.maven_blog.mapper.VisitorMapper;
import xyz.lightseekers.maven_blog.service.IVisitorService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class VisitorServiceImpl implements IVisitorService {


    @Autowired
    private VisitorMapper visitorMapper;

    /**
     * 查找所有的visitor记录
     *
     * @return visitor的集合
     * @throws RuntimeException
     */
    @Override
    public List<Visitor> selectAllVisitor() throws RuntimeException {
        VisitorExample visitorExample = new VisitorExample();
        return visitorMapper.selectByExample(visitorExample);
    }

    /**
     * 查找某个url的访问记录
     *
     * @param url
     * @return 访问该url的visitor集合
     * @throws RuntimeException
     */
    @Override
    public List<Visitor> selectAllByURl(String url) throws RuntimeException {
        VisitorExample visitorExample = new VisitorExample();
        visitorExample.createCriteria().andUrlEqualTo(url);
        return visitorMapper.selectByExample(visitorExample);
    }


    /**
     * 查找某一个ip的访问记录
     *
     * @param ip
     * @return 访问该ip的访问者记录
     * @throws RuntimeException
     */
    @Override
    public List<Visitor> selectAllByIp(String ip) throws RuntimeException {
        VisitorExample visitorExample = new VisitorExample();
        visitorExample.createCriteria().andIpEqualTo(ip);
        return visitorMapper.selectByExample(visitorExample);
    }

    /**
     * 通过id删除访问记录
     *
     * @param id
     * @return
     * @throws RuntimeException
     */
    @Override
    public int deleteById(int id) throws RuntimeException {
        return visitorMapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入访问记录
     *
     * @param visitor visitor对象
     * @return insert的成功与否的值
     * @throws RuntimeException
     */
    @Override
    public int insert(Visitor visitor) throws RuntimeException {
        return visitorMapper.insert(visitor);
    }

    /**
     * 修改访问记录
     *
     * @param visitor
     * @return update成功与否的值
     * @throws RuntimeException
     */
    @Override
    public int update(Visitor visitor) throws RuntimeException {
        return visitorMapper.updateByPrimaryKey(visitor);
    }

    /**
     * 批量删除
     *
     * @param ids 输入要删除的对象的id数组
     * @return
     * @throws RuntimeException
     */
    @Override
    public int deleteByBatch(int[] ids) throws RuntimeException {
        for (int id : ids) {
            int i = visitorMapper.deleteByPrimaryKey(id);
        }
        return 1;
    }

    @Override
    public void download(HttpServletResponse response) throws RuntimeException {
        List<Visitor> visitors = selectAllVisitor();
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
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
        //具体内容
        for (int i = 0; i < visitors.size(); i++) {
            XSSFRow rowi = sheet.createRow(2 + i);
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
        try {
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("访课信息" + ".xlsx", "utf-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}