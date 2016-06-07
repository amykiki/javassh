package org.mybatis.smvc.spring;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.smvc.entity.User;
import org.mybatis.smvc.entity.UserFind;
import org.mybatis.smvc.service.UserService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Amysue on 2016/6/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:beans.xml"})
public class JsonTest {
    @Resource(name = "userService")
    private UserService userService;
    private Gson gson = new Gson();

    @Test
    public void testJson1() {
        User u = userService.load(1);
        String ujson = gson.toJson(u);
        System.out.println(ujson);
        User u2 = gson.fromJson(ujson, User.class);
        System.out.println(u2);
    }

    @Test
    public void testListJson() {
        UserFind uf = new UserFind();
        PageInfo<User> pages = userService.findByPager(uf, 1);
        List<User> users = pages.getList();
        Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
        String ujson = gson2.toJson(users);
        System.out.println(ujson);
    }
}
