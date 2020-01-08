package xyz.lightseekers.maven_blog.mapper.ex;

import xyz.lightseekers.maven_blog.bean.ex.LoveExcle;

import java.util.List;

public interface LoveExcelMapper {

    List<LoveExcle> selectAll();

    List<LoveExcle> selectLoveExcelByBlogId(int id);
}
