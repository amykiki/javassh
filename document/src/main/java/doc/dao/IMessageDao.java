package doc.dao;

import doc.entity.Message;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by Amysue on 2016/5/13.
 */
public interface IMessageDao extends IBaseDao<Message>{
    public Message LoadEagerById(int id);
    public List findByCriteria(DetachedCriteria query);
}
