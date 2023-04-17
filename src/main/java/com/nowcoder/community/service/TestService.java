package com.nowcoder.community.service;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service // 业务注解
//@Scope("prototype") 多例
public class TestService {

    public TestService() {
        System.out.println("实例化TestService");
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化TestService");
    }

    @PreDestroy
    public void destory() {
        System.out.println("销毁TestService");
    }

    @Async
    public void execute1() {
        System.out.println("execute1");
    }

    @Scheduled(initialDelay = 10000, fixedRate = 1000)
    public void execute2() {
        System.out.println("execute2");
    }

}
