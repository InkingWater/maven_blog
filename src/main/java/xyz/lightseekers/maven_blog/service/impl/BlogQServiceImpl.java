package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Blog;
import xyz.lightseekers.maven_blog.bean.ex.BlogCountByMonth;
import xyz.lightseekers.maven_blog.bean.ex.BlogUserTypeEXQ;
import xyz.lightseekers.maven_blog.bean.ex.BlogUserTypeWithoutArticleEXQ;
import xyz.lightseekers.maven_blog.mapper.BlogMapper;
import xyz.lightseekers.maven_blog.mapper.ex.BlogEXQMapper;
import xyz.lightseekers.maven_blog.service.IBlogQService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BlogQServiceImpl implements IBlogQService {

    @Autowired
    private BlogEXQMapper blogEXQMapper;

    @Autowired
    private BlogMapper blogMapper;


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
    public List<BlogUserTypeEXQ> searchByBlogTitle(String titleWord) throws RuntimeException {
        titleWord = "%" + titleWord + "%";
        return blogEXQMapper.searchByBlogTitle(titleWord);
    }

    @Override
    public int insert(Blog blog) throws RuntimeException {
        blog.setDate(new Date());
        return blogEXQMapper.insert(blog);
    }

    @Override
    public List<BlogUserTypeEXQ> selectAll() throws RuntimeException {
        return blogEXQMapper.selectAll();
    }

    @Override
    public List<BlogUserTypeWithoutArticleEXQ> selectAllWithoutArticle() throws RuntimeException {
        return blogEXQMapper.selectAllWithoutArticle();
    }

    @Override
    public BlogUserTypeEXQ selectById(int id) throws RuntimeException {
        return blogEXQMapper.selectById(id);
    }

    @Override
    public List<BlogUserTypeEXQ> selectBetweenDate(Date lastDate, Date nextDate) throws RuntimeException {
        return blogEXQMapper.selectBetweenDate(lastDate, nextDate);
    }


    @Override
    public List<BlogUserTypeWithoutArticleEXQ> selectByTypeId(int typeId) throws RuntimeException {
        return blogEXQMapper.selectByTypeId(typeId);


    }

    @Override
    public List<BlogUserTypeEXQ> selectToday() throws RuntimeException {
        return blogEXQMapper.selectToday();
    }

    @Override
    public List<BlogCountByMonth> selectDayCountByMonth() throws RuntimeException {
        Calendar calendar_30 = Calendar.getInstance();
        calendar_30.set(calendar_30.get(Calendar.YEAR), calendar_30.get(Calendar.MONTH), calendar_30.get(Calendar.DAY_OF_MONTH) + 1 - 30, 0, 0, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) + 1, 0, 0, 0);
        List<BlogCountByMonth> listOld = blogEXQMapper.selectDayCountByMonth(calendar_30.getTime(), calendar.getTime());
        List<BlogCountByMonth> listNew = new ArrayList<BlogCountByMonth>();
        for (int i = 0; i < 30; i++) {
            Calendar calendarList = Calendar.getInstance();
            calendarList.set(calendarList.get(Calendar.YEAR), calendarList.get(Calendar.MONTH), calendarList.get(Calendar.DAY_OF_MONTH) + 1 - 30 + i, 0, 0, 0);
            long time = calendarList.getTimeInMillis();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String d = format.format(time);
            BlogCountByMonth blogCountByMonth=new BlogCountByMonth();
            blogCountByMonth.setDatetime(d);
            listNew.add(blogCountByMonth);
        }


        for (int i = 0; i < listNew.size(); i++) {
            for (int j = 0; j < listOld.size(); j++) {
                if (listNew.get(i).getDatetime().equals(listOld.get(j).getDatetime())) {
                    listNew.get(i).setBlogCount(listOld.get(j).getBlogCount());
                    break;
                } else {
                    listNew.get(i).setBlogCount(0);
                }
            }
        }

        System.out.println(listOld.get(listOld.size()-2).getDatetime());
        System.out.println(listNew.get(listNew.size()-2).getDatetime());


        return listNew;
    }


//    public static long sub(Date date) {
//        long t1 = date.getTime();
//        String s = "2592000000";
//        long t2 = Long.valueOf(s).longValue();
//        t1 = t1 - t2;
//        return t1;
//    }
//    public static Calendar sub() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) + 1 - 31, 0, 0, 0);
//        return calendar;
}
