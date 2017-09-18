package com.test.exception;

/**
 * Author: jiyanbin@zafh.com.cn
 * JDK: 1.8
 * Created on 16/1/28.
 */
public class CLSException extends RuntimeException {
    private String code;

    public CLSException(String message) {
        super(message);
    }

    public CLSException(String message, Throwable cause) {
        super(message, cause);
    }

    public CLSException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CLSException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CLSException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }
}
