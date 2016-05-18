package doc.service;

import doc.dao.IAttachmentDao;
import doc.dao.IMessageDao;
import doc.dao.IUserMessageDao;
import doc.dto.AttachDto;
import doc.dto.Pager;
import doc.entity.Attachment;
import doc.entity.Message;
import doc.entity.User;
import doc.entity.UserMessage;
import doc.util.ActionUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Amysue on 2016/5/13.
 */
@Service("msgService")
public class MessageService implements IMessageService {
    private IMessageDao msgDao;
    private IUserMessageDao userMsgDao;
    private IAttachmentService attachService;

    @Resource(name = "msgDao")
    public void setMsgDao(IMessageDao msgDao) {
        this.msgDao = msgDao;
    }

    @Resource(name = "attachService")
    public void setAttachService(IAttachmentService attachService) {
        this.attachService = attachService;
    }

    @Resource(name = "userMsgDao")
    public void setUserMsgDao(IUserMessageDao userMsgDao) {
        this.userMsgDao = userMsgDao;
    }

    @Override
    public void add(Message msg, List<Integer> sendToIds, AttachDto attachDto) {
        List<Attachment> attsList = null;
        if (attachDto != null) {
            attsList = attachService.add(attachDto);
        }
        msg.setCreateDate(new Date());
        msg.setAuthor(ActionUtil.getLguser());
        if (attsList != null) {
            msg.setAttachments(attsList);
        }
        msgDao.add(msg);
        UserMessage umAuthor = new UserMessage();
        umAuthor.setUser(ActionUtil.getLguser());
        umAuthor.setMessage(msg);
        umAuthor.setRead(true);
        umAuthor.setSend(true);
        userMsgDao.add(umAuthor);
        for (int uid: sendToIds) {
            UserMessage um = new UserMessage();
            User u = new User();
            u.setId(uid);
            um.setUser(u);
            um.setMessage(msg);
            um.setRead(false);
            um.setSend(false);
            userMsgDao.add(um);
        }
    }

    @Override
    public void add(Message msg, Attachment attachment) {
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(attachment);
//        add(msg, attachments);
    }

    @Override
    public void add(Message msg) {
        msgDao.add(msg);
    }

    @Override
    public void delete(int id) {
        Message m = loadEagerById(id);
        if (m.getAttachments() != null) {
            List<Integer> aids = new ArrayList<>();
            for (Attachment a : m.getAttachments()) {
                aids.add(a.getId());
                System.out.println(a.getId());
            }
//            attachDao.deleteAttachments(aids);
        }
        msgDao.delete(id);
    }

    @Override
    public Message load(int id) {
        Message m = msgDao.load(id);
        return m;
    }

    @Override
    public Message loadEagerById(int id) {
        return msgDao.LoadEagerById(id);
    }

    @Override
    public Pager<Message> findSendMsg(Map<String, Object> params, int pageOffset) {
        Pager<Message> pager = msgDao.findSendMsg(params, pageOffset);
        return pager;
    }

    @Override
    public Pager<Message> findReceiveMsg(Map<String, Object> params, int pageOffset) {
        return null;
    }
}
