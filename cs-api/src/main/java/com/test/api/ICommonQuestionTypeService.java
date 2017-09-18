package com.test.api;


import com.test.model.CommonQuestionType;
import com.test.util.DataPage;

import java.util.List;

/**
 * @author esther
 * @create 2017-09-11 11:29
 * 常见问题类别
 */

public interface ICommonQuestionTypeService {
    /**
     * 增加常见问题类别
     *
     * @param commonQuestionType
     * @return
     */
    int add(CommonQuestionType commonQuestionType);

    /**
     * 修改常见问题类别
     *
     * @param commonQuestionType
     * @return
     */
    int update(CommonQuestionType commonQuestionType);

    /**
     * 删除常见问题类别
     *
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 根据id获取常见问题类别
     *
     * @param id
     * @return
     */
    CommonQuestionType get(Integer id);

    /**
     * 获取常见问题类别列表
     *
     * @param name
     * @param status
     * @param page
     * @param pageSize
     * @return
     */
    DataPage<CommonQuestionType> getList(String name, Short status, int page, int pageSize);

    /**
     * 获取所有的问题类别列表
     * @return
     */
    List<CommonQuestionType> getAllList();
}
