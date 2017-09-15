package com.test.dao;

import com.test.examples.CommonQuestionExample;
import com.test.model.CommonQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonQuestionMapper {
    int countByExample(CommonQuestionExample example);

    int deleteByExample(CommonQuestionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommonQuestion record);

    int insertSelective(CommonQuestion record);

    List<CommonQuestion> selectByExampleWithBLOBs(CommonQuestionExample example);

    List<CommonQuestion> selectByExample(CommonQuestionExample example);

    CommonQuestion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommonQuestion record, @Param("example") CommonQuestionExample example);

    int updateByExampleWithBLOBs(@Param("record") CommonQuestion record, @Param("example") CommonQuestionExample example);

    int updateByExample(@Param("record") CommonQuestion record, @Param("example") CommonQuestionExample example);

    int updateByPrimaryKeySelective(CommonQuestion record);

    int updateByPrimaryKeyWithBLOBs(CommonQuestion record);

    int updateByPrimaryKey(CommonQuestion record);
}