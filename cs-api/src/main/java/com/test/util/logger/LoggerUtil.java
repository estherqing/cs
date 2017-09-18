package com.test.util.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;

/**
 * Created by jiyanbin on 15/7/5.
 * <p/>
 * 获取Logger的工厂接口，当业务对象不在Spring容器中管理时，直接调用此工厂获取Logger实现
 */
public class LoggerUtil {

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    public static LoggerWrapper getLoggerWrapper(@NotNull Class<?> clazz) {
        return new LoggerWrapper(getLogger(clazz));
    }

    public static LoggerWrapper getLoggerWrapper(@NotNull String name) {
        return new LoggerWrapper(LoggerFactory.getLogger(name));
    }
}
