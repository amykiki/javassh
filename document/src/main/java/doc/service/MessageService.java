package doc.service;

import doc.dao.IMessageDao;
import doc.dao.IUserMessageDao;
import doc.dto.AttachDto;
import doc.dto.Pager;
import doc.dto.SystemContext;
import doc.entity.Attachment;
import doc.entity.Message;
import doc.entity.User;
import doc.entity.UserMessage;
import doc.exception.DocException;
import doc.util.ActionUtil;
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
        Set<Attachment> attsSet = null;
        if (attachDto != null) {
            attsSet = attachService.add(attachDto);
        }
        msg.setCreateDate(new Date());
        msg.setAuthor(ActionUtil.getLguser());
        msg.setDeleted(false);
        if (attsSet != null) {
            msg.setAttachments(attsSet);
        }
        msgDao.add(msg);
        for (int uid: sendToIds) {
            UserMessage um = new UserMessage();
            User u = new User();
            u.setId(uid);
            um.setUser(u);
            um.setMessage(msg);
            um.setRead(false);
            um.setDeleted(false);
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
        Message m = load(id);
        if (m.getAuthor().getId() == ActionUtil.getLguser().getId()) {
            m.setDeleted(true);
        }
    }

    @Override
    public void deleteReceive(int id) {
        UserMessage um = loadByMsgId(id);
        if (um != null) {
            um.setDeleted(true);
        }
    }

    @Override
    public Message load(int id) {
        Message m = msgDao.load(id);
        return m;
    }

    @Override
    public UserMessage loadByMsgId(int mid) {
        User lguser = SystemContext.getLguser();
        String hql = "select um from UserMessage um where um.message.id = ? and user.id = ?";
        UserMessage um = (UserMessage)userMsgDao.queryByHQL(hql, mid, lguser.getId());
        return um;
    }

    @Override
    public Message loadEagerById(int id) {
        return msgDao.LoadEagerById(id);
    }

    @Override
    public Pager<Message> findSendMsg(Map<String, Object> params, int pageOffset) {
        params.put("fromuser", ActionUtil.getLguser().getId());
        Pager<Message> pager = msgDao.findSendMsg(params, pageOffset);
        return pager;
    }

    @Override
    public Pager<Message> findReceiveMsg(Map<String, Object> params, int pageOffset) {
        params.put("touser", ActionUtil.getLguser().getId());
        Pager<Message> pager = msgDao.findReceiveMsg(params, pageOffset);
        return pager;
    }

    @Override
    public Message loadSendMsg(int id) throws DocException{
        User lguser = ActionUtil.getLguser();
//       User lguser = SystemContext.getLguser();
        Message m;
        String hql = "select count(id) from Message where id = ? and author.id = ? and deleted = false";
        long o = (Long)msgDao.queryByHQL(hql, id, lguser.getId());
        if (o == 1) {
            hql = "select msg from Message msg " +
                    "left join fetch msg.attachments " +
                    "left join fetch msg.receives re " +
                    "left join fetch re.user ruser " +
                    "left join fetch ruser.dep " +
                    "where msg.id = ?";
            m = (Message) msgDao.queryByHQL(hql, id);
        } else {
            throw new DocException("错误的操作，信息不存在或者已被删除");
        }
        return m;
    }

    @Override
    public Message updateReceiveMsg(int id) throws DocException {
        User lguser = ActionUtil.getLguser();
        Message m;
        String hql = "select um from UserMessage um where um.message.id = ? and um.user.id = ? and deleted = false";
        UserMessage um = (UserMessage)msgDao.queryByHQL(hql, id, lguser.getId());
        if (um != null) {
            hql = "select msg from Message msg " +
                    "left join fetch msg.author au " +
                    "left join fetch au.dep " +
                    "left join fetch msg.attachments " +
                    "where msg.id = ?";
            m = (Message) msgDao.queryByHQL(hql, id);
            if (!um.isRead()) {
                um.setRead(true);
            }
        } else {
            throw new DocException("错误的操作，信息不存在或者已被删除");
        }
        return m;
    }
}
