package doc.dao;

import doc.dto.Pager;
import doc.entity.Message;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;
import java.util.Map;

/**
 * Created by Amysue on 2016/5/13.
 */
public interface IMessageDao extends IBaseDao<Message>{
    public Message LoadEagerById(int id);
    public List findByCriteria(DetachedCriteria query);

    public Pager<Message> findSendMsg(Map<String, Object> params, int pageOffset);
    public Pager<Message> findReceiveMsg(Map<String, Object> params, int pageOffset);
}
