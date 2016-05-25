package org.mybatis.smvc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.smvc.entity.Department;
import org.mybatis.smvc.entity.User;
import org.mybatis.smvc.enums.Role;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Amysue on 2016/5/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:beans.xml"})
public class UserServiceTest {

    @Resource(name = "userService")
    private UserService userService;

    @Test
    public void testAdd() throws Exception {
        User u = new User();
        u.setEmail("amysue.z@gmail.com");
        u.setNickname("testamy");
        u.setPassword("1234321");
        u.setRole(Role.ADMIN);
        u.setUsername("amy01");
        u.setDep(new Department(17));
        userService.add(u);
    }
}