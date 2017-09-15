package com.test.dao;

import com.test.model.CommonQuestionType;
import com.test.examples.CommonQuestionTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonQuestionTypeMapper {
    int countByExample(CommonQuestionTypeExample example);

    int deleteByExample(CommonQuestionTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommonQuestionType record);

    int insertSelective(CommonQuestionType record);

    List<CommonQuestionType> selectByExample(CommonQuestionTypeExample example);

    CommonQuestionType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommonQuestionType record, @Param("example") CommonQuestionTypeExample example);

    int updateByExample(@Param("record") CommonQuestionType record, @Param("example") CommonQuestionTypeExample example);

    int updateByPrimaryKeySelective(CommonQuestionType record);

    int updateByPrimaryKey(CommonQuestionType record);
}