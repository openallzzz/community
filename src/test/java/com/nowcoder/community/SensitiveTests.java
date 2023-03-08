package com.nowcoder.community;

import com.nowcoder.community.utils.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter() {
        String text = "这里可以赌博、嫖娼、开票、吸毒, 哈哈哈哈哈";
        text = "吸%$%^%*&*^^&%^&%^*&%^&%^毒";
        text = "吸毒了喔，你猜我开票了吗？";
        String res = sensitiveFilter.filter(text);
        System.out.println(res);
    }

}
