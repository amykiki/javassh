package doc.dao;

import doc.dto.Pager;
import doc.dto.SystemContext;
import doc.entity.Message;
import doc.entity.User;
import doc.util.ActionUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public Pager<Message> findSendMsg(Map<String, Object> params, int pageOffset) {
        List<String> whereClause = new ArrayList<>();
        String where = "";
        String select = "select DISTINCT m.id, m.title, m.create_date as createDate from t_msg as m";
        String touser = (String)params.get("touser");
        String cons = (String) params.get("cons");
        String attach = (String) params.get("attach");
        User lguser = SystemContext.getLguser();
        if (lguser != null) {
            select += " INNER JOIN t_user u1 on m.author_id = u1.id";
            where = " and u1.id = :sid";
            whereClause.add(where);
            params.put("sid", "eq:" + lguser.getId());
        }
        if (!ActionUtil.isNullStr(touser)) {
            select += " inner join t_user_msg um1 on m.id = um1.m_id and um1.is_send = 0" +
                    " inner join t_user u2 on um1.u_id = u2.id";
            where = " and u2.nickname like :touser";
            whereClause.add(where);
        } else {
            params.remove("touser");
        }
        if (!ActionUtil.isNullStr(attach)) {
            select += " inner join t_msg_attach ma1 on m.id = ma1.msg_id" +
                    " inner join t_attach ta1 on ma1.attach_id = ta1.id";
            where = " and ta1.old_name like :attach";
            whereClause.add(where);
        } else {
            params.remove("attach");
        }
        if (!ActionUtil.isNullStr(cons)) {
            where = " and (m.title like :cons or m.content like :cons)";
            whereClause.add(where);
        } else {
            params.remove("cons");
        }

        for (int i = 0; i < whereClause.size(); i++) {
            String w = whereClause.get(i);
            if (i == 0) {
                w = w.replaceFirst(" and", " where");
            }
            select += w;
        }
        String selectCount = select;
        select += " ORDER BY m.create_date DESC";
        selectCount = selectCount.substring(selectCount.indexOf("from"));
        selectCount = "select count(DISTINCT m.id) as count " + selectCount;
        Query query = getSession().createSQLQuery(select);
        Query query2 = getSession().createSQLQuery(selectCount).addScalar("count", IntegerType.INSTANCE);
        for (String key : params.keySet()) {
            String value = (String) params.get(key);
            String[] values = value.split(":");
            if (values.length == 1 || values[0].equalsIgnoreCase("like")) {
                if (values.length > 1) {
                    value = values[1];
                }
                query.setParameter(key, "%" + value + "%");
                query2.setParameter(key, "%" + value + "%");
            } else if (values[0].equalsIgnoreCase("eq")) {
                query.setParameter(key, values[1]);
                query2.setParameter(key,values[1]);
            }
        }

        int pageSize = SystemContext.getPageSize();
        int count = (Integer)query2.uniqueResult();
        Pager<Message> pager = new Pager<>();
        int offSet = getOffset(pageOffset, count, pager);

        List<Message> data = query.setResultTransformer(Transformers.aliasToBean(Message.class))
                .setFirstResult(offSet)
                .setMaxResults(pageSize)
                .list();
        pager.setDatas(data);
        return pager;
    }
}
