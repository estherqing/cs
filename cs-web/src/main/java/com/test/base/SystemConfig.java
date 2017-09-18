package com.test.base;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;


public class SystemConfig {
    /* 15 */   //private Logger log = LoggerFactory.getLogger(SystemConfig.class);

    /* 17 */   public static String EMAIL_USERNAME = "email.username";


    private String configFile;
    private Properties property;

    public void init() {
        try {
            this.property = PropertiesLoaderUtils.loadAllProperties(this.configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getString(String key) {
/* 67 */
        return this.property.getProperty(key);
    }

    public Integer getInt(String key) {
/* 71 */
        String value = this.property.getProperty(key);
/* 72 */
        return StringUtils.isEmpty(value) ? null : Integer.valueOf(value);
    }

    public Double getDouble(String key) {
/* 76 */
        String value = this.property.getProperty(key);
/* 77 */
        return StringUtils.isEmpty(value) ? null : Double.valueOf(value);
    }

    public void setValue(String key, String value) {
/* 81 */
        this.property.setProperty(key, value);
    }

    public void setConfigFile(String configFile) {
/* 85 */
        this.configFile = configFile;
    }
}
