package com.test.api;

import com.test.model.CommonQuestion;
import com.test.util.DataPage;

/**
 * @author esther
 * @create 2017-09-11 11:29
 * 常见问题
 */

public interface ICommonQuestionService {
    /**
     * 增加常见问题
     *
     * @param commonQuestion
     * @return
     */
    int add(CommonQuestion commonQuestion);

    /**
     * 修改常见问题
     *
     * @param commonQuestion
     * @return
     */
    int update(CommonQuestion commonQuestion);

    /**
     * 删除常见问题
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 获取常见问题内容
     *
     * @param id
     * @return
     */
    CommonQuestion get(Long id);

    /**
     * 浏览次数加一
     *
     * @param id
     * @return
     */
    int updateCount(Long id);

    /**
     * 启用、禁用
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, Short status);

    /**
     * 获取常见问题列表
     *
     * @param title
     * @param status
     * @param page
     * @param pageSize
     * @return
     */
    DataPage<CommonQuestion> getList(String title, String content, Integer typeId, Short status, Integer page, Integer pageSize);
}
