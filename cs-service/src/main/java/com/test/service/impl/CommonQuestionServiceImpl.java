package com.test.service.impl;

import com.test.api.ICommonQuestionService;
import com.test.dao.CommonQuestionMapper;
import com.test.model.CommonQuestion;
import com.test.util.DataPage;
import com.test.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author esther
 * @create 2017-09-11 11:46
 * 常见问题服务
 */
@Service("commonQuestionService")
public class CommonQuestionServiceImpl implements ICommonQuestionService {
    @Autowired
    private CommonQuestionMapper commonQuestionMapper;

    @Override
    public int add(CommonQuestion commonQuestion) {
        return commonQuestionMapper.add(commonQuestion);
    }

    @Override
    public int update(CommonQuestion commonQuestion) {
        return commonQuestionMapper.update(commonQuestion);
    }

    @Override
    public int delete(Long id) {
        return commonQuestionMapper.delete(id);
    }

    @Override
    public CommonQuestion get(Long id) {
        return commonQuestionMapper.get(id);
    }

    @Override
    public int updateCount(Long id) {
        return commonQuestionMapper.updateCount(id);
    }

    @Override
    public int updateStatus(Long id, Short status) {
        return commonQuestionMapper.updateStatus(id, status);
    }

    @Override
    public DataPage<CommonQuestion> getList(String title, String content, Integer typeId, Short status, Integer page, Integer pageSize) {
        DataPage<CommonQuestion> dataPage = new DataPage<>();
        Paging paging = new Paging();
        paging.setCurrentPage(page);
        paging.setPageSize(pageSize);
        List<CommonQuestion> list = commonQuestionMapper.getList(title, content, typeId, status, paging);
        dataPage.setData(list);
        dataPage.setCurrentPage(paging.getCurrentPage());
        dataPage.setPageSize(paging.getPageSize());
        dataPage.setTotalItem(paging.getTotalItem());
        return dataPage;
    }
}
