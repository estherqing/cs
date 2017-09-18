package com.test;

import java.io.Serializable;

/**
 * Created by jiyanbin on 15/8/29.
 */
public class LoginResult implements Serializable {
    private int code;
    private String token;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
