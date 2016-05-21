package service;

import doc.dto.SystemContext;
import doc.entity.Attachment;
import doc.entity.Message;
import doc.entity.User;
import doc.service.IAttachmentService;
import doc.service.IMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.*;

/**
 * Created by Amysue on 2016/5/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:beans.xml"})
public class MessageServiceTest {

    @Resource(name = "msgService")
    private IMessageService msgService;
    @Resource(name = "attachService")
    private IAttachmentService attachService;

    @Test
    public void testSetMsgDao() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        Attachment attachment = new Attachment();
        attachment.setCreateDate(new Date());
        Attachment attachment2 = new Attachment();
        attachment2.setCreateDate(new Date());
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(attachment);
        attachments.add(attachment2);
        Message message = new Message();
//        msgService.add(message, attachments);
    }

    @Test
    public void testAdd1() throws Exception {

    }

    @Test
    public void testAdd2() throws Exception {
        Message m = new Message();
        msgService.add(m);
    }

    @Test
    public void testDelete() throws Exception {
        msgService.delete(3);

    }

    @Test
    public void testLoad() throws Exception {
        Message m = msgService.load(5);

    }

    @Test
    public void testFindSendMsg() throws Exception {
        SystemContext.setPageSize(15);
        SystemContext.setPageRange(10);
        User u = new User();
        u.setId(176);
//        u.setId(2);

                            SystemContext.setLguser(u);
        Map<String, Object> params = new HashMap<>();
//        params.put("touser", "沈");
        params.put("fromuser", "无敌架构师");
        params.put("attach", ".jpg");
//        params.put("cons", "测试");
//        msgService.findSendMsg(params, 0);
//        msgService.findReceiveMsg(params, 0);
    }

    @Test
    public void testLoadSend() throws Exception {

        User lguser = new User();
        lguser.setId(1);
        SystemContext.setLguser(lguser);
        Message m = msgService.loadSendMsg(3);
        System.out.println(1);
    }

    @Test
    public void testUpdateReceiveMsg() throws Exception {
        User lguser = new User();
        lguser.setId(2);
        SystemContext.setLguser(lguser);
        Message m = msgService.updateReceiveMsg(3);
        System.out.println(m.getTitle());
        System.out.println(m.getAuthor().getNickname());
        System.out.println(m.getAttachments().size());
        Iterator<Attachment> iter = m.getAttachments().iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().getOldName());
        }
        System.out.println(1);
    }

    @Test
    public void testLoadEagerById() throws Exception {
        Message m = msgService.loadEagerById(3);
        System.out.println(1);
    }
}