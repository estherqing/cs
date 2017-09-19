package com.test.ueditor;

import com.baidu.ueditor.ConfigManager;
import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.hunter.ImageHunter;
import com.baidu.ueditor.upload.Uploader;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ActionEnter
{
    private HttpServletRequest request = null;

    private String rootPath = null;
    private String contextPath = null;

    private String actionType = null;

    private ConfigManager configManager = null;

    public ActionEnter(HttpServletRequest request, String rootPath)
    {
        this.request = request;
        this.rootPath = rootPath;
        // 对action进行赋值。
        this.actionType = request.getParameter("action");
        this.contextPath = request.getContextPath();
        // 构建configManager类
        this.configManager = ConfigManager.getInstance(this.rootPath, this.contextPath, request.getRequestURI());
    }

    public String exec()
    {
        // 这个是处理jsonp的形式，一般都是不跨域的。
        String callbackName = this.request.getParameter("callback");

        if (callbackName != null)
        {
            if (!validCallbackName(callbackName)) {
                return new BaseState(false, 401).toJSONString();
            }

            return callbackName + "(" + invoke() + ");";
        }

        return invoke();
    }

    public String invoke()
    {
        // 判断action是否合法，如果不合法返回一个非法状态
        if ((this.actionType == null) || (!ActionMap.mapping.containsKey(this.actionType))) {
            return new BaseState(false, 101).toJSONString();
        }
        // 如果找不到configManager也报错
        if ((this.configManager == null) || (!this.configManager.valid())) {
            return new BaseState(false, 102).toJSONString();
        }

        State state = null;

        // 取得actionCode
        int actionCode = ActionMap.getType(this.actionType);

        Map conf = null;

        switch (actionCode)
        {
            case 0:
                return this.configManager.getAllConfig().toString();
            case 1:
            case 2:
            case 3:
            case 4:
                // 处理上传文件
                conf = this.configManager.getConfig(actionCode);
                state = new Uploader(this.request, conf).doExec();
                break;
            case 5:
                conf = this.configManager.getConfig(actionCode);
                String[] list = this.request.getParameterValues((String)conf.get("fieldName"));
                // 处理在线编辑
                state = new ImageHunter(conf).capture(list);
                break;
            case 6:
            case 7:
                conf = this.configManager.getConfig(actionCode);
                int start = getStartIndex();
                // 处理文件list
                state = new FileManager(conf).listFile(start);
        }

        return state.toJSONString();
    }

    public int getStartIndex()
    {
        String start = this.request.getParameter("start");
        try
        {
            return Integer.parseInt(start); } catch (Exception e) {
        }
        return 0;
    }

    public boolean validCallbackName(String name)
    {
        if (name.matches("^[a-zA-Z_]+[\\w0-9_]*$")) {
            return true;
        }

        return false;
    }
}
