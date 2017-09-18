package com.test.dao;

import com.test.examples.CommonQuestionExample;
import com.test.model.CommonQuestion;
import com.test.util.Paging;
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

    int add(CommonQuestion commonQuestion);

    int update(CommonQuestion commonQuestion);

    int delete(Long id);

    CommonQuestion get(Long id);

    int updateCount(Long id);

    int updateStatus(@Param("id") Long id,@Param("status") Short status);

    int getCount(@Param("title") String title,@Param("content") String content,@Param("typeId") Integer typeId,@Param("status") Short status);

    List<CommonQuestion> getList(@Param("title") String title,@Param("content") String content,@Param("typeId") Integer typeId,@Param("status") Short status,@Param("page") Paging page);
}