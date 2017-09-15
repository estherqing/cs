package com.test.service.impl;

import com.test.api.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author esther
 * @create 2017-09-08 19:07
 * $DESCRIPTION}
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    public void sayHello(String name) {
        System.out.println("Hello: " + name);
    }
}
