package com.deming.blog.mapper;

import com.deming.blog.domain.Mlfruit;
import com.deming.blog.domain.MlfruitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MlfruitMapper {
    long countByExample(MlfruitExample example);

    int deleteByExample(MlfruitExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Mlfruit record);

    int insertSelective(Mlfruit record);

    List<Mlfruit> selectByExample(MlfruitExample example);

    Mlfruit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Mlfruit record, @Param("example") MlfruitExample example);

    int updateByExample(@Param("record") Mlfruit record, @Param("example") MlfruitExample example);

    int updateByPrimaryKeySelective(Mlfruit record);

    int updateByPrimaryKey(Mlfruit record);
}