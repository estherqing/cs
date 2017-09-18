package com.test.util.logger;

/**
 * Author: jiyanbin@zafh.com.cn
 * Date: 13:08
 * JDK: 1.7
 * Logger对象感知接口。
 * <p/>
 * 由BeanPostProcessor处理
 */
public interface LoggerWrapperAware {

    public void setLogger(LoggerWrapper logger) ;
}

/**
 * $Log: Create By jiyanbin At 13:08
 * **************************************************Modify history*************************************************
 * Date                Modifier               Cause
 *
 * *****************************************************************************************************************
 */