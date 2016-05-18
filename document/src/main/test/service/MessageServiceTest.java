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
        User u = new User();
        u.setId(2);
        SystemContext.setPageSize(15);
        SystemContext.setPageRange(10);
        SystemContext.setLguser(u);
        Map<String, Object> params = new HashMap<>();
        params.put("touser", "沈");
        params.put("attach", ".jpg");
        params.put("cons", "正文");
        msgService.findSendMsg(params, 0);
    }
}