package com.nowcoder.community.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Service // 业务注解
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

}
