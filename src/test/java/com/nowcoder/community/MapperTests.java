package com.nowcoder.community;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectUser() {
//        System.out.println(userMapper.selectById(101));

        System.out.println(userMapper.selectByName("liubei"));

        System.out.println(userMapper.selectByEmail("nowcoder103@sina.com"));
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setType(1);
        user.setStatus(2);
        user.setSalt("ppoop");
        user.setPassword("zzzzzzz");
        user.setHeaderUrl("test");
        user.setActivationCode("1");
        user.setEmail("asas");
        user.setCreateTime(new Date());

        Integer rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateUser() {
//        userMapper.updateHeader(150, "zzzzzzz");
//        userMapper.updatePassword(150, "123");
        userMapper.updateStatus(150, 1);
    }

}
