package com.test.service.impl;

import com.test.api.IUserService;

/**
 * @author esther
 * @create 2017-09-08 19:07
 * $DESCRIPTION}
 */

public class UserServiceImpl implements IUserService {
    public void sayHello(String name) {
        System.out.println("Hello: " + name);
    }
}
