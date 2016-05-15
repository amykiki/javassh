package doc.service;

import doc.dao.IAttachmentDao;
import doc.dao.IMessageDao;
import doc.entity.Attachment;
import doc.entity.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amysue on 2016/5/13.
 */
@Service("msgService")
public class MessageService implements IMessageService {
    private IMessageDao msgDao;
    private IAttachmentDao attachDao;

    @Resource(name = "msgDao")
    public void setMsgDao(IMessageDao msgDao) {
        this.msgDao = msgDao;
    }

    @Resource(name ="attachDao" )
    public void setAttachDao(IAttachmentDao attachDao) {
        this.attachDao = attachDao;
    }

    @Override
    public void add(Message msg, List<Attachment> attachments) {
        msg.setAttachments(attachments);
        msgDao.add(msg);
    }

    @Override
    public void add(Message msg, Attachment attachment) {
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(attachment);
        add(msg, attachments);
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
            attachDao.deleteAttachments(aids);
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
}
