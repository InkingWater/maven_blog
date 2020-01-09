package xyz.lightseekers.maven_blog.service.impl;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Love;
import xyz.lightseekers.maven_blog.bean.User;
import xyz.lightseekers.maven_blog.bean.ex.LoveEX;
import xyz.lightseekers.maven_blog.bean.ex.LoveDataExcelEX;
import xyz.lightseekers.maven_blog.mapper.LoveMapper;
import xyz.lightseekers.maven_blog.mapper.UserMapper;
import xyz.lightseekers.maven_blog.mapper.ex.LoveEXMapper;
import xyz.lightseekers.maven_blog.service.ILoveService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoveServiceImpl implements ILoveService {

    @Autowired
    private LoveEXMapper loveEXMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoveMapper loveMapper;

    /**
     * 产找所有的love对象
     *
     * @return love的对象集合 包括对应id的user和blog对象
     * @throws RuntimeException
     */
    @Override
    public List<LoveEX> selectAll() throws RuntimeException {
        return loveEXMapper.selectAllUser();
    }

    /**
     * 根据user的id查找对应的对象
     *
     * @param id user的id
     * @return user对象
     * @throws RuntimeException
     */
    @Override
    public User selectById(int id) throws RuntimeException {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 查找所有点赞了的用户
     *
     * @return user集合
     * @throws RuntimeException
     */
    @Override
    public List<User> selectListById() throws RuntimeException {
        List<LoveEX> loveEXMS = loveEXMapper.selectAllUser();
        List<User> users = new ArrayList<>();
        for (LoveEX loveEXM : loveEXMS) {
            User user = selectById(loveEXM.getUser().getId());
            user.setPassword(null);
            if (!users.contains(user)) {
                users.add(user);
            }
        }
        return users;
    }

    /**
     * 根据博客查找给该博客点赞的user
     *
     * @param id 博客的id
     * @return user集合
     * @throws RuntimeException
     */
    @Override
    public List<User> selectAllByBlog(int id) throws RuntimeException {
        List<Integer> list = loveEXMapper.selectByBlog(id);
        List<User> list1 = new ArrayList<>();
        for (Integer integer : list) {
            User user = userMapper.selectByPrimaryKey(integer);
            user.setPassword(null);
            if (!list1.contains(user)) {
                list1.add(user);
            }
        }
        return list1;
    }

    /**
     * 在love表中插入数据
     *
     * @param love love
     * @return
     * @throws RuntimeException
     */
    @Override
    public int insert(Love love) throws RuntimeException {
        return loveMapper.insert(love);
    }

    /**
     * 通过id删除love中的记录
     *
     * @param id love的id值
     * @return delete方法值
     * @throws RuntimeException
     */
    @Override
    public int deleteLoveById(int id) throws RuntimeException {
        return loveMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新love表中的数据
     *
     * @param love 传入整个love对象
     * @return update
     * @throws RuntimeException
     */
    @Override
    public int update(Love love) throws RuntimeException {
        return loveMapper.updateByPrimaryKey(love);
    }

    /**
     * 通过id查找对应的love记录
     *
     * @param id love的id
     * @return LoveEX对象 包括user blog子对象
     * @throws RuntimeException
     */
    @Override
    public List<LoveEX> selectByID(int id) throws RuntimeException {
        return loveEXMapper.selectById(id);
    }

    @Override
    public List<LoveEX> selectAllLoveByBlog(int id) throws RuntimeException {
        return loveEXMapper.selectAllByBlog(id);
    }

    @Override
    public int deleteBatch(int[] ids) throws RuntimeException {
        for (int id : ids) {
            loveMapper.deleteByPrimaryKey(id);
        }
        return 1;
    }

    @Override
    public List<LoveDataExcelEX> selectAllExcel() throws RuntimeException {
        return loveEXMapper.selectAllUserExcel();
    }

    @Override
    public List<LoveDataExcelEX> selectLoveExcelByBlogId(int id) throws RuntimeException {
        return loveEXMapper.selectLoveExcelByBlogId(id);
    }

    @Override
    public void downloadDatabaseByBlog(HttpServletResponse response, int id) throws RuntimeException {
        List<LoveDataExcelEX> loveExcels = selectLoveExcelByBlogId(id);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(loveExcels.get(0).getbName() + "的点赞信息");
        //表头
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellType(CellType.STRING);
        cell.setCellValue("点赞信息集合");

        //第二行
        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("点赞序号");
        row1.createCell(1).setCellValue("点赞用户id");
        row1.createCell(2).setCellValue("点赞用户名称");
        row1.createCell(3).setCellValue("点赞ip");
        row1.createCell(4).setCellValue("点赞经度");
        row1.createCell(5).setCellValue("点赞纬度");
        row1.createCell(6).setCellValue("点赞flag");

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
        //内容
        for (int i = 0; i < loveExcels.size(); i++) {
            XSSFRow rowi = sheet.createRow(2 + i);
            rowi.createCell(0).setCellValue(loveExcels.get(i).getId());
            rowi.createCell(1).setCellValue(loveExcels.get(i).getUserId());
            rowi.createCell(2).setCellValue(loveExcels.get(i).getuName());
            rowi.createCell(3).setCellValue(loveExcels.get(i).getIp());
            rowi.createCell(4).setCellValue(loveExcels.get(i).getLongitude());
            rowi.createCell(5).setCellValue(loveExcels.get(i).getLatitude());
            rowi.createCell(6).setCellValue(loveExcels.get(i).getFlag());
        }
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("点赞的表" + ".xlsx", "utf-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
