package com.test.ueditor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public final class ConfigManager
{
    private final String rootPath;
    private final String originalPath;
    private final String contextPath;
    private static final String configFileName = "config.json";
    private String parentPath = null;
    private JSONObject jsonConfig = null;
    private static final String SCRAWL_FILE_NAME = "scrawl";
    private static final String REMOTE_FILE_NAME = "remote";

    private ConfigManager(String rootPath, String contextPath, String uri)
            throws FileNotFoundException, IOException
    {
        rootPath = rootPath.replace("\\", "/");

        this.rootPath = rootPath;
        this.contextPath = contextPath;
        // 这个地方要特别注意，originalPath其实就是controller.jsp所在的路径
        if (contextPath.length() > 0)
            this.originalPath = (this.rootPath + uri.substring(contextPath.length()));
        else {
            this.originalPath = (this.rootPath + uri);
        }

        initEnv();
    }

    public static ConfigManager getInstance(String rootPath, String contextPath, String uri)
    {
        try
        {
            return new ConfigManager(rootPath, contextPath, uri); } catch (Exception e) {
        }
        return null;
    }

    public boolean valid()
    {
        return this.jsonConfig != null;
    }

    public JSONObject getAllConfig()
    {
        return this.jsonConfig;
    }

    public Map<String, Object> getConfig(int type)
    {
        Map conf = new HashMap();
        String savePath = null;
        // 根据不同的code来解析config.json的配置文件
        switch (type)
        {
            case 4:
                conf.put("isBase64", "false");
                conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("fileMaxSize")));
                conf.put("allowFiles", getArray("fileAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("fileFieldName"));
                savePath = this.jsonConfig.getString("filePathFormat");
                break;
            case 1:
                conf.put("isBase64", "false");
                conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("imageMaxSize")));
                conf.put("allowFiles", getArray("imageAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("imageFieldName"));
                savePath = this.jsonConfig.getString("imagePathFormat");
                break;
            case 3:
                conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("videoMaxSize")));
                conf.put("allowFiles", getArray("videoAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("videoFieldName"));
                savePath = this.jsonConfig.getString("videoPathFormat");
                break;
            case 2:
                conf.put("filename", "scrawl");
                conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("scrawlMaxSize")));
                conf.put("fieldName", this.jsonConfig.getString("scrawlFieldName"));
                conf.put("isBase64", "true");
                savePath = this.jsonConfig.getString("scrawlPathFormat");
                break;
            case 5:
                conf.put("filename", "remote");
                conf.put("filter", getArray("catcherLocalDomain"));
                conf.put("maxSize", Long.valueOf(this.jsonConfig.getLong("catcherMaxSize")));
                conf.put("allowFiles", getArray("catcherAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("catcherFieldName") + "[]");
                savePath = this.jsonConfig.getString("catcherPathFormat");
                break;
            case 7:
                conf.put("allowFiles", getArray("imageManagerAllowFiles"));
                conf.put("dir", this.jsonConfig.getString("imageManagerListPath"));
                conf.put("count", Integer.valueOf(this.jsonConfig.getInt("imageManagerListSize")));
                break;
            case 6:
                conf.put("allowFiles", getArray("fileManagerAllowFiles"));
                conf.put("dir", this.jsonConfig.getString("fileManagerListPath"));
                conf.put("count", Integer.valueOf(this.jsonConfig.getInt("fileManagerListSize")));
        }

        conf.put("savePath", savePath);
        conf.put("rootPath", this.rootPath);

        return conf;
    }

    // 加载config.json配置文件
    private void initEnv()
            throws FileNotFoundException, IOException
    {
        File file = new File(this.originalPath);

        if (!file.isAbsolute()) {
            file = new File(file.getAbsolutePath());
        }

        this.parentPath = file.getParent();

        String configContent = readFile(getConfigPath());
        try
        {
            JSONObject jsonConfig = new JSONObject(configContent);
            this.jsonConfig = jsonConfig;
        } catch (Exception e) {
            this.jsonConfig = null;
        }
    }

    private String getConfigPath()
    {
        return this.parentPath + File.separator + "config.json";
    }

    private String[] getArray(String key)
    {
        JSONArray jsonArray = this.jsonConfig.getJSONArray(key);
        String[] result = new String[jsonArray.length()];

        int i = 0; for (int len = jsonArray.length(); i < len; i++) {
        result[i] = jsonArray.getString(i);
    }

        return result;
    }


    // 读取config.json里面的内容
    private String readFile(String path)
            throws IOException
    {
        StringBuilder builder = new StringBuilder();
        try
        {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
            BufferedReader bfReader = new BufferedReader(reader);

            String tmpContent = null;

            while ((tmpContent = bfReader.readLine()) != null) {
                builder.append(tmpContent);
            }

            bfReader.close();
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
        }

        return filter(builder.toString());
    }

    private String filter(String input)
    {
        return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");
    }
}