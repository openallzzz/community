package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

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

    @Test
    public void testSelectPosts() {
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for(DiscussPost discussPost : list) {
            System.out.println(discussPost);
        }

        System.out.println(discussPostMapper.selectDiscussPostRows(0));
    }

    @Test
    public void testLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(110);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

        System.out.println(loginTicketMapper.insertLoginTicket(loginTicket));
    }

}
