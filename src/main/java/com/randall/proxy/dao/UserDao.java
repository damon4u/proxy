package com.randall.proxy.dao;

import com.randall.proxy.entity.User;
import org.apache.ibatis.annotations.Insert;

public interface UserDao {

    @Insert("INSERT IGNORE INTO user (user_id, nickname, avatar_url, create_time) " +
            "VALUES (#{userId}, #{nickname}, #{avatarUrl}, now())")
    int save(User user);

}
