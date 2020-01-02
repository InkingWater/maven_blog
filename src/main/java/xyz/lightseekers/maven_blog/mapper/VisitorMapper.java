package xyz.lightseekers.maven_blog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.lightseekers.maven_blog.bean.Visitor;
import xyz.lightseekers.maven_blog.bean.VisitorExample;

public interface VisitorMapper {
    long countByExample(VisitorExample example);

    int deleteByExample(VisitorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Visitor record);

    int insertSelective(Visitor record);

    List<Visitor> selectByExample(VisitorExample example);

    Visitor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Visitor record, @Param("example") VisitorExample example);

    int updateByExample(@Param("record") Visitor record, @Param("example") VisitorExample example);

    int updateByPrimaryKeySelective(Visitor record);

    int updateByPrimaryKey(Visitor record);
}