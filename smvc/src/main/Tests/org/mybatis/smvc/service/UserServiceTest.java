package org.mybatis.smvc.service;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.smvc.entity.Department;
import org.mybatis.smvc.entity.User;
import org.mybatis.smvc.enums.Role;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        System.out.println(u.getId());
    }

    @Test
    public void testList() throws Exception {
        List<User> users = userService.list();

        for (User u : users) {
            System.out.println(u);
        }

        // cache test
        System.out.println("TEST2 Begin");
        List<User> users2 = userService.list();

        for (User u : users2) {
            System.out.println(u);
        }
    }

    @Test
    public void testAddUsers() throws Exception {
        User u = new User();
        u.setEmail("amysue.z@gmail.com");
        u.setNickname("testamy");
        u.setPassword("1234321");
        u.setRole(Role.ADMIN);
        u.setUsername("amy01");
        u.setDep(new Department(17));
        u.setId(0);
        User u2 = new User();
        BeanUtils.copyProperties(u2, u);
        u2.setNickname("testamy02");
        u2.setDep(new Department(20));
        u2.setId(0);
        User u3 = new User();
        BeanUtils.copyProperties(u3, u);
        u3.setNickname("testamy03");
        u3.setDep(new Department(7));
        u3.setRole(Role.NORMAL);
        u3.setId(0);
        List<User> users = new ArrayList<>();
        users.add(u);
        users.add(u2);
        users.add(u3);
        userService.addUsers(users);
        System.out.println(1);
    }

    @Test
    public void testAddByMap() throws Exception {
        Map<String, Object> umap = new HashedMap();
        umap.put("username", "amymap");
        umap.put("role", Role.ADMIN);
        umap.put("dep", new Department(20));
        userService.addByMap(umap);

    }

    @Test
    public void testFindByMap() throws Exception {
        Map<String, Object> umap = new HashedMap();
//        umap.put("username", "amy");
        umap.put("dep", new Department("办公室"));
//        umap.put("role", Role.NORMAL);
        List<User> data = userService.findByMap(umap);
        System.out.println(data.size());
    }

    @Test
    public void testLoadByParam() throws Exception {
//        User u = userService.loadByParam(493);
        List<String> cols = new ArrayList<>();
       /* cols.add("username");
        cols.add("nickname");
        cols.add("email");*/
        cols.add("id");
        cols.add("role");
        User u = userService.loadByParam(1, cols);
        System.out.println(u);

    }

    @Test
    public void testListAllSendUsers() throws Exception {
        List<User> users = userService.listAllSendUsers(2);
        System.out.println("test");
    }
}