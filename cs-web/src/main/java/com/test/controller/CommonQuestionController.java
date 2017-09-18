package com.test.controller;

import com.test.api.ICommonQuestionService;
import com.test.base.AbstractBaseController;
import com.test.manager.UploadConstant;
import com.test.manager.ZaUploadManager;
import com.test.model.CommonQuestion;
import com.test.model.ZaUser;
import com.test.util.DataPage;
import com.test.util.ResultDO;
import com.test.util.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

/**
 * @author esther
 * @create 2017-09-11 15:23
 * $DESCRIPTION}
 */
@Controller
@RequestMapping("/question")
public class CommonQuestionController extends AbstractBaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonQuestionController.class);

    @Autowired
    private ICommonQuestionService commonQuestionService;

    @ResponseBody
    @RequestMapping("/add")
    public String add(HttpServletRequest httpServletRequest, CommonQuestion commonQuestion) {
        if (commonQuestion == null || commonQuestion.getTypeId() == null) {
            return WebUtil.getFailureJson("常见问题类别不能为空！");
        }
        if (commonQuestion.getTitle() == null) {
            return WebUtil.getFailureJson("标题不能为空");
        }
        if (commonQuestion.getContent() == null) {
            return WebUtil.getFailureJson("内容不能为空");
        }
        if (commonQuestion.getPhone() == null) {
            return WebUtil.getFailureJson("咨询电话不能为空");
        }
        ZaUser user = getZaUser(httpServletRequest);
        commonQuestion.setUserId(user.getUid());
        commonQuestion.setUserName(user.getUsername());
        commonQuestion.setUserRealName(user.getRealname());
        commonQuestion.setStatus(Short.valueOf("1"));
        commonQuestion.setBrowseCount(0l);
        commonQuestion.setCreateTime(new Date());
        commonQuestion.setModifyTime(new Date());
        Integer rs = commonQuestionService.add(commonQuestion);
        if (rs != null && rs > 0) {
            return WebUtil.getSuccessJson("新增成功！");
        }
        return WebUtil.getFailureJson("新增失败！");
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(CommonQuestion commonQuestion) {
        if (commonQuestion == null || commonQuestion.getId() == null) {
            return WebUtil.getFailureJson("常见问题ID不能为空！");
        }
        if (commonQuestion.getTypeId() == null) {
            return WebUtil.getFailureJson("常见问题类别不能为空！");
        }
        if (commonQuestion.getTitle() == null) {
            return WebUtil.getFailureJson("标题不能为空");
        }
        if (commonQuestion.getContent() == null) {
            return WebUtil.getFailureJson("内容不能为空");
        }
        if (commonQuestion.getPhone() == null) {
            return WebUtil.getFailureJson("咨询电话不能为空");
        }
        Integer rs = commonQuestionService.update(commonQuestion);
        if (rs != null && rs > 0) {
            return WebUtil.getSuccessJson("更新成功！");
        }
        return WebUtil.getFailureJson("更新失败！");
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Long id) {
        if (id == null) {
            return WebUtil.getFailureJson("常见问题ID不能为空！");
        }
        Integer rs = commonQuestionService.delete(id);
        if (rs != null && rs > 0) {
            return WebUtil.getSuccessJson("删除成功！");
        }
        return WebUtil.getFailureJson("删除失败！");
    }

    @ResponseBody
    @RequestMapping("/get")
    public String get(Long id) {
        if (id == null) {
            return WebUtil.getFailureJson("常见问题ID不能为空！");
        }
        // 浏览次数加一
        commonQuestionService.updateCount(id);
        CommonQuestion rs = commonQuestionService.get(id);
        if (rs != null) {
            return WebUtil.getSuccessJson(rs);
        }
        return WebUtil.getFailureJson("常见问题不存在！");
    }

    @ResponseBody
    @RequestMapping("/enOrDisable")
    public String updateStatus(Long id, Short status) {
        if (id == null || status == null) {
            return WebUtil.getFailureJson("问题ID或者状态不能为空！");
        }
        int rs = commonQuestionService.updateStatus(id, status);
        if (rs > 0) {
            return WebUtil.getSuccessJson("启用或者禁用成功！");
        }
        return WebUtil.getFailureJson("启用或者禁用出错啦！");
    }

    @ResponseBody
    @RequestMapping("/list")
    public String list(@RequestParam(value = "title", required = false) String title,
                       @RequestParam(value = "content", required = false) String content,
                       @RequestParam(value = "typeId", required = false) Integer typeId,
                       @RequestParam(value = "status", required = false) Short status,
                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        DataPage<CommonQuestion> dataPage = commonQuestionService.getList(title, content, typeId, status, page, pageSize);
        return WebUtil.getSuccessJson(dataPage);
    }

    @ResponseBody
    @RequestMapping("/upload")
    public String upload(HttpServletRequest request) {
        String urlServer = "";

        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                String fileName = file.getOriginalFilename();
                if (StringUtils.isEmpty(fileName) || file.getSize() < 0) {
                    LOGGER.error("文件名称为空或者文件内容为空");
                    continue;
                }
                String fileFormat = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                try {
                    byte[] imgData = file.getBytes();
                    ResultDO<String> ossPath = ZaUploadManager.uploadFile(UploadConstant.Systype.CLS, UploadConstant.FileNamespace.COMMON_QUESTION, imgData, UUID
                            .randomUUID().toString() + "." + fileFormat);
                    if (ossPath == null || !ossPath.isSuccess()) {
                        throw new RuntimeException("文件上传失败，调用阿里云失败。");
                    }
                    if (urlServer != "") {
                        urlServer += ";";
                    }
                    urlServer += ossPath.getMsg();
                } catch (IOException e) {
                    LOGGER.error("上传失败", e);
                    return WebUtil.getFailureJson("上传失败");
                }

                // 得到上传服务器的路径
               /* String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
                if (file != null) {
                    path += file.getOriginalFilename();
                    try {
                        *//* 构建文件目录 *//*
                        File fileDir = new File(path);
                        if (!fileDir.exists()) {
                            fileDir.mkdirs();
                        }
                        //上传
                        file.transferTo(fileDir);
                        if (urlServer != "") {
                            urlServer += ";";
                        }
                        urlServer += path;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }*/
            }
        }
        return WebUtil.getSuccessJson("上传成功", urlServer, null);
    }
}
