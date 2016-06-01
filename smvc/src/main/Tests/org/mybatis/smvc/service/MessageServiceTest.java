package org.mybatis.smvc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.smvc.entity.Message;
import org.mybatis.smvc.entity.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.enterprise.inject.New;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Amysue on 2016/5/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:beans.xml"})
public class MessageServiceTest {

    @Resource(name = "msgService")
    private MessageService msgService;
    @Test
    public void testLoad() throws Exception {
        Message m2 = msgService.load(5);
        System.out.println(1);
    }

    @Test
    public void testFindSend() throws Exception {
        Map<String, Object> fParmas = new HashMap<>();
        fParmas.put("delete", false);
//        fParmas.put("cons", "java");
        User author = new User();
        author.setId(1);
        fParmas.put("author", author);
        List<Message> msgs = msgService.findSend(fParmas);

        System.out.println("debug stop");
    }
}