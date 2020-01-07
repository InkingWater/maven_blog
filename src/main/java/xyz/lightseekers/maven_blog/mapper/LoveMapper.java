package xyz.lightseekers.maven_blog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.Love;
import xyz.lightseekers.maven_blog.bean.LoveExample;
@Repository
public interface LoveMapper {
    long countByExample(LoveExample example);

    int deleteByExample(LoveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Love record);

    int insertSelective(Love record);

    List<Love> selectByExample(LoveExample example);

    Love selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Love record, @Param("example") LoveExample example);

    int updateByExample(@Param("record") Love record, @Param("example") LoveExample example);

    int updateByPrimaryKeySelective(Love record);

    int updateByPrimaryKey(Love record);
}