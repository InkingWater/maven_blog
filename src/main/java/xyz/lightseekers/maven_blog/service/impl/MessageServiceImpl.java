package xyz.lightseekers.maven_blog.service.impl;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.ex.MessageEX;
import xyz.lightseekers.maven_blog.mapper.MessageMapper;
import xyz.lightseekers.maven_blog.mapper.ex.MessageEXMapper;
import xyz.lightseekers.maven_blog.service.IMessageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;


@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private MessageEXMapper messageEXMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int insertMessage(xyz.lightseekers.maven_blog.bean.Message message) throws RuntimeException {
        return messageMapper.insert(message);
    }

    /**
     * 根据id删除留言
     *
     * @param id
     * @throws RuntimeException
     */
    @Override
    public int deleteMessage(int id) throws RuntimeException {
        return messageMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据用户名查找相应留言,不输入会查找全部留言
     *
     * @param name
     * @return
     * @throws RuntimeException
     */
    @Override
    public List<MessageEX> selectByName(String name) throws RuntimeException {
        if ((name == null || "".equals(name))) {
            return selectAll();
        } else if (!"".equals(name)) {
            //前者为空 后者不为空
            name = "%" + name + "%";
            return messageEXMapper.selectByName(name);
        }
        return null;
    }

    /**
     * 查找全部留言
     *
     * @return
     * @throws RuntimeException
     */
    @Override
    public List<MessageEX> selectAll() throws RuntimeException {
        List<MessageEX> list = messageEXMapper.selectAll();
        return list;
    }

    @Override
    public String deleteMessages(int[] id) throws RuntimeException {
        for (int i = 0; i < id.length; i++) {
            deleteMessage(id[i]);
        }
        return null;
    }

    @Override
    public void download(HttpServletResponse response) throws RuntimeException {
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
        List<MessageEX> messages = selectAll();
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
        response.setHeader("content-Type", "application/vnd.ms-excel");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("留言.xlsx", "utf-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
