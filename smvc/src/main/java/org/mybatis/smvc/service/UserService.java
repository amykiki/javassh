package org.mybatis.smvc.service;

import org.mybatis.smvc.entity.User;
import org.mybatis.smvc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Amysue on 2016/5/25.
 */
@Service("userService")
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int add(User user) {
        int result = userMapper.add(user);
        return result;
    }
}
