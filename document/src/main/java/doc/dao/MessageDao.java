package doc.dao;

import doc.entity.Message;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Amysue on 2016/5/13.
 */
@Repository("msgDao")
public class MessageDao extends BaseDao<Message> implements IMessageDao {
    @Override
    public Message LoadEagerById(int id) {
        Query query = getSession().createQuery("select msg from Message as msg left join fetch msg.attachments where msg.id = :id");
        query.setParameter("id", id);
        Message m = (Message)query.uniqueResult();
        return m;
    }

    @Override
    public List findByCriteria(DetachedCriteria query) {
        Query query2 = getSession().createQuery("select msg from Message as msg left join fetch msg.attachments");
        List<Message> mlists = query2.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return null;
    }
}
