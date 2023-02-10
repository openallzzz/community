package com.nowcoder.community;

import com.nowcoder.community.dao.testDao;
import com.nowcoder.community.service.TestService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void testApplicationContext() {
//        System.out.println(applicationContext);

        // 面向接口编程
//        testDao dao = applicationContext.getBean(testDao.class);
//        System.out.println(dao.select());
    }

    @Test
    public void testService() {
//        TestService service = applicationContext.getBean(TestService.class);
//        System.out.println(service);
//        service.init();
//        service.destory();
    }

    @Test
    public void testConfig() {
//        SimpleDateFormat simpleDateFormat =
//                applicationContext.getBean(SimpleDateFormat.class);
//
//        System.out.println(simpleDateFormat.format(new Date()));
    }
}
