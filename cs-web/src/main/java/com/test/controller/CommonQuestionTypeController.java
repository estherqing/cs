package com.test.controller;

import com.test.api.ICommonQuestionTypeService;
import com.test.base.AbstractBaseController;
import com.test.model.CommonQuestionType;
import com.test.util.DataPage;
import com.test.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author esther
 * @create 2017-09-11 15:23
 * $DESCRIPTION}
 */
@Controller
@RequestMapping("/question/type")
public class CommonQuestionTypeController extends AbstractBaseController {
    @Autowired
    private ICommonQuestionTypeService commonQuestionTypeService;

    @ResponseBody
    @RequestMapping("/add")
    public String add(HttpServletRequest httpServletRequest, CommonQuestionType commonQuestionType) {
        if (commonQuestionType == null || commonQuestionType.getName() == null) {
            return WebUtil.getFailureJson("常见问题类别名称不能为空！");
        }
//        ZaUser user = getZaUser(httpServletRequest);
//        commonQuestionType.setUserId(user.getUid());
//        commonQuestionType.setUserName(user.getUsername());
//        commonQuestionType.setUserRealName(user.getRealname());
        commonQuestionType.setStatus(Short.valueOf("1"));
        commonQuestionType.setCreateTime(new Date());
        commonQuestionType.setModifyTime(new Date());
        Integer rs = commonQuestionTypeService.add(commonQuestionType);
        if (rs != null && rs > 0) {
            System.out.println(WebUtil.getSuccessJson("新增成功！"));
            return WebUtil.getSuccessJson("新增成功！");
        }
        return WebUtil.getFailureJson("新增失败！");
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(CommonQuestionType commonQuestionType) {
        if (commonQuestionType == null || commonQuestionType.getId() == null || commonQuestionType.getName() == null) {
            return WebUtil.getFailureJson("常见问题类别ID和名称不能为空！");
        }
        Integer rs = commonQuestionTypeService.update(commonQuestionType);
        if (rs != null && rs > 0) {
            return WebUtil.getSuccessJson("更新成功！");
        }
        return WebUtil.getFailureJson("更新失败！");
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Integer id) {
        if (id == null) {
            return WebUtil.getFailureJson("常见问题类别ID不能为空！");
        }
        Integer rs = commonQuestionTypeService.delete(id);
        if (rs != null && rs > 0) {
            return WebUtil.getSuccessJson("删除成功！");
        }
        return WebUtil.getFailureJson("删除失败！");
    }

    @ResponseBody
    @RequestMapping("/get")
    public String get(Integer id) {
        if (id == null) {
            return WebUtil.getFailureJson("常见问题类别ID不能为空！");
        }
        CommonQuestionType rs = commonQuestionTypeService.get(id);
        if (rs != null) {
            return WebUtil.getSuccessJson(rs);
        }
        return WebUtil.getFailureJson("常见问题类别不存在！");
    }

    @ResponseBody
    @RequestMapping("/list")
    public String list(@RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "status", required = false) Short status,
                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        DataPage<CommonQuestionType> dataPage = commonQuestionTypeService.getList(name, status, page, pageSize);
        return WebUtil.getSuccessJson(dataPage);
    }

    @ResponseBody
    @RequestMapping("/allList")
    public String list() {
        List<CommonQuestionType> dataList = commonQuestionTypeService.getAllList();
        return WebUtil.getSuccessJson(dataList);
    }
}
