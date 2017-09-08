package com.test.util;

import com.google.common.util.concurrent.AbstractIdleService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动dubbo服务器端服务
 */
public class DubboStart extends AbstractIdleService {
    private ClassPathXmlApplicationContext context;

    public static void main(String[] args) {
        DubboStart bootstrap = new DubboStart();
        bootstrap.startAsync();
        try {
            Object lock = new Object();
            synchronized (lock) {
                while (true) {
                    lock.wait();
                }
            }
        } catch (InterruptedException ex) {
            System.err.println("ignoreinterruption");
        }
    }

    @Override
    protected void shutDown() throws Exception {
        context.stop();
        System.out.println("-------------service stoppedsuccessfully-------------");
    }

    @Override
    protected void startUp() throws Exception {
//        context = new ClassPathXmlApplicationContext("spring/cls-bss-context.xml");
        context = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        context.start();
        context.registerShutdownHook();
        System.out.println("----------------jia-service provider service startedsuccessfully------------");

    }
}
