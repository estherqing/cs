package com.test.service.impl;

import com.test.api.ICommonQuestionTypeService;
import com.test.dao.CommonQuestionTypeMapper;
import com.test.model.CommonQuestionType;
import com.test.util.DataPage;
import com.test.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author esther
 * @create 2017-09-11 11:45
 * 常见问题类别服务
 */
@Service("commonQuestionTypeService")
public class CommonQustionTypeServiceImpl implements ICommonQuestionTypeService {
    @Autowired
    private CommonQuestionTypeMapper commonQuestionTypeMapper;

    @Override
    public int add(CommonQuestionType commonQuestionType) {
        return commonQuestionTypeMapper.add(commonQuestionType);
    }

    @Override
    public int update(CommonQuestionType commonQuestionType) {
        return commonQuestionTypeMapper.update(commonQuestionType);
    }

    @Override
    public int delete(Integer id) {
        return commonQuestionTypeMapper.delete(id);
    }

    @Override
    public CommonQuestionType get(Integer id) {
        return commonQuestionTypeMapper.get(id);
    }

    @Override
    public DataPage<CommonQuestionType> getList(String name, Short status, int page, int pageSize) {
        DataPage<CommonQuestionType> dataPage = new DataPage<>();
        Paging paging = new Paging();
        paging.setCurrentPage(page);
        paging.setPageSize(pageSize);
        List<CommonQuestionType> list = commonQuestionTypeMapper.getList(name, status, paging);
        dataPage.setData(list);
        dataPage.setCurrentPage(paging.getCurrentPage());
        dataPage.setPageSize(paging.getPageSize());
        dataPage.setTotalItem(paging.getTotalItem());
        return dataPage;
    }

    @Override
    public List<CommonQuestionType> getAllList() {
        return commonQuestionTypeMapper.getAllList();
    }
}
