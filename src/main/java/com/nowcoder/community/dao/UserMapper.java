package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectById(Integer id);

    User selectByName(String username);

    User selectByEmail(String email);

    Integer insertUser(User user);

    Integer updateStatus(Integer id, Integer status);

    Integer updateHeader(Integer id, String headerUrl);

    Integer updatePassword(Integer id, String password);
}
