package service;

import doc.entity.Department;
import doc.entity.User;
import doc.enums.Role;
import doc.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Random;

/**
 * Created by Amysue on 2016/5/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class UserServiceTest {
    Random ran = new Random();
    @Resource(name = "userService")
    private IUserService userService;

    @Test
    public void testSetUserDao() throws Exception {

    }

    @Test
    public void testSetDepDao() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        User u = new User();
    /*    u.setUsername("amy");
        u.setPassword("12333");
        u.setNickname("无敌小公主");
        u.setRole(Role.ADMIN);
        u.setEmail("amysue.z@gmail.com"); */
        u.setUsername("kevinqqq");
        u.setPassword("12345");
        u.setNickname("超级架构师222");
        u.setRole(Role.NORMAL);
        u.setEmail("kevin222@126.com");
        userService.add(u, 9);

    }

    @Test
    public void testDelete() throws Exception {
        userService.delete(10);
    }

    @Test
    public void testLoadByUsername() throws Exception {
        User u = userService.loadLazyByUsername("amy");
        System.out.println(u.getUsername());
    }

    @Test
    public void testLoad() throws Exception {
        User u = userService.load(1);
        System.out.println(u);
    }

    @Test
    public void testUpdate() throws Exception {
        User u = new User();
        u.setUsername("kevin9");
        u.setPassword("12345");
        u.setNickname("无敌架构师");
        u.setRole(Role.ADMIN);
        u.setEmail("kevin@qq.com");
        u.setId(2);
        userService.update(u, 2);
    }

    @Test
    public void testFindUser() throws Exception {

    }

    @Test
    public void testLoadById() throws Exception {
        User u = userService.loadEagerById(1);
        System.out.println(u);
    }

    @Test
    public void testAddMultipleUsers() throws Exception {
        int[] deps = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10,
                               11, 13, 16, 17, 18, 19, 20};
        for (int i = 0; i < deps.length; i++) {
            int k = 1;
            while (k <= 30) {
                User u = new User();
                u.setUsername("user" + i + k);
                u.setEmail("user" + i + k + "@amy_doc.com");
                u.setPassword("1234");
                u.setNickname(getName());
//                u.setRole( );
                k--;
            }

        }
    }

    private String getName() {
        String[] name1 = new String[]{"孔","张","叶","李","叶入","孔令",
                                      "张立","陈","刘","牛","夏侯","令","令狐","赵","母","穆","倪",
                                      "张毅","称","程","王","王志","刘金","冬","吴","马","沈"};

        String[] name2 = new String[]{"凡","课","颖","页","源","都",
                                      "浩","皓","西","东","北","南","冲","昊","力","量","妮",
                                      "敏","捷","杰","坚","名","生","华","鸣","蓝","春","虎","刚","诚"};

        String[] name3 = new String[]{"吞","明","敦","刀","备","伟",
                                      "唯","楚","勇","诠","佺","河","正","震","点","贝","侠",
                                      "伟","大","凡","琴","青","林","星","集","财"};

        boolean two = ran.nextInt(50)>=45?false:true;
        if(two) {
            String n1 = name1[ran.nextInt(name1.length)];
            String n2;
            int n = ran.nextInt(11);
            if(n>5) {
                n2 = name2[ran.nextInt(name2.length)];
            } else {
                n2 = name3[ran.nextInt(name3.length)];
            }
            return n1+n2;
        } else {
            String n1 = name1[ran.nextInt(name1.length)];
            String n2 = name2[ran.nextInt(name2.length)];
            String n3 = name3[ran.nextInt(name3.length)];
            return n1+n2+n3;
        }
    }
}