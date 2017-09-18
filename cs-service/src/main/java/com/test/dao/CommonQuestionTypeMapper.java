package com.test.dao;

import com.test.examples.CommonQuestionTypeExample;
import com.test.model.CommonQuestionType;
import com.test.util.Paging;
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

    int add(CommonQuestionType commonQuestionType);

    int update(CommonQuestionType commonQuestionType);

    int delete(Integer id);

    CommonQuestionType get(Integer id);

    int getCount(@Param("name") String name,@Param("status") Short status);

    List<CommonQuestionType> getList(@Param("name") String name,@Param("status") Short status,@Param("page") Paging page);

    List<CommonQuestionType> getAllList();
}