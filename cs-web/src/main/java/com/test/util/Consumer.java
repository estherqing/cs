package com.test.util;

import com.test.api.IUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) {
        //测试常规服务
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("consumer start");
        IUserService userService1 = context.getBean(IUserService.class);
        System.out.println("consumer");
        userService1.sayHello("world");
    }
}
