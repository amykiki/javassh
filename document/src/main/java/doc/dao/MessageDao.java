package doc.dao;

import doc.dto.Pager;
import doc.dto.SystemContext;
import doc.entity.Message;
import doc.entity.User;
import doc.util.ActionUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
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
        Message m = (Message) query.uniqueResult();
        return m;
    }

    @Override
    public List findByCriteria(DetachedCriteria query) {
        Query         query2 = getSession().createQuery("select msg from Message as msg left join fetch msg.attachments");
        List<Message> mlists = query2.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return null;
    }

    @Override
    public Pager<Message> findSendMsg(Map<String, Object> params, int pageOffset) {
        String       select      = "select DISTINCT m.id, m.title, m.create_date as createDate from t_msg as m";
        Pager<Message> pager = findMsg(params, pageOffset, select);
        return null;
    }

    @Override
    public Pager<Message> findReceiveMsg(Map<String, Object> params, int pageOffset) {
        String select = "select DISTINCT m.id, m.title, m.create_date as createDate, u1.id as uid, u1.nickname from t_msg as m";
        Pager<Message> pager = findMsg(params, pageOffset, select);
        return null;
    }

    public Pager<Message> findMsg(Map<String, Object> params, int pageOffset, String select) {
        List<String> whereClause = new ArrayList<>();
        String       where       = "";
        String       type        = (String) params.get("type");
        String       fromuser    = (String) params.get("fromuser");
        String       touser      = (String) params.get("touser");
        String       cons        = (String) params.get("cons");
        String       attach      = (String) params.get("attach");

        if (!ActionUtil.isNullStr(fromuser)) {
            select += " INNER JOIN t_user u1 on m.author_id = u1.id";
            if (type.equalsIgnoreCase("send")) {
                where = " and u1.id = :fromuser";
                whereClause.add(where);
                params.put("fromuser", "eq:" + fromuser);
            } else if (type.equalsIgnoreCase("receive")) {
                where = " and u1.nickname like :fromuser";
                whereClause.add(where);
            }
        }

        if (!ActionUtil.isNullStr(touser)) {
            select += " inner join t_user_msg um1 on m.id = um1.m_id and um1.is_send = 0";
            if (type.equalsIgnoreCase("send")) {
                select += " inner join t_user u2 on um1.u_id = u2.id";
                where = " and u2.nickname like :touser";
                whereClause.add(where);
            } else if (type.equalsIgnoreCase("receive")) {
                where = " and um1.u_id = :touser";
                whereClause.add(where);
                params.put("touser", "eq:" + touser);
            }
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
        params.remove("type");

        for (int i = 0; i < whereClause.size(); i++) {
            String w = whereClause.get(i);
            if (i == 0) {
                w = w.replaceFirst(" and", " where");
            }
            select += w;
        }
        //设置select count 函数
        String selectCount = select;
        selectCount = selectCount.substring(selectCount.indexOf("from"));
        selectCount = "select count(DISTINCT m.id) as count " + selectCount;
        // 设置查询结果顺序
        select += " ORDER BY m.create_date DESC";
        //生成查询query，包括查询数目和查询列表的
        Query query  = getSession().createSQLQuery(select);
        Query query2 = getSession().createSQLQuery(selectCount).addScalar("count", IntegerType.INSTANCE);
        // 设置查询参数
        for (String key : params.keySet()) {
            String   value  = (String) params.get(key);
            String[] values = value.split(":");
            if (values.length == 1 || values[0].equalsIgnoreCase("like")) {
                if (values.length > 1) {
                    value = values[1];
                }
                query.setParameter(key, "%" + value + "%");
                query2.setParameter(key, "%" + value + "%");
            } else if (values[0].equalsIgnoreCase("eq")) {
                query.setParameter(key, values[1]);
                query2.setParameter(key, values[1]);
            }
        }

        int            pageSize = SystemContext.getPageSize();
        int            count    = (Integer) query2.uniqueResult();
        Pager<Message> pager    = new Pager<>();
        int            offSet   = getOffset(pageOffset, count, pager);

        List<Message> data = new ArrayList<>();
        query.setFirstResult(offSet);
        query.setMaxResults(pageSize);
        if (type.equalsIgnoreCase("send")) {
            data = query.setResultTransformer(Transformers.aliasToBean(Message.class))
                    .list();

        } else {
            List list = query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                 .list();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = (Map)list.get(i);
                Message msg = new Message();
                msg.setTitle((String) map.get("title"));
                msg.setId((int)map.get("id"));
                msg.setCreateDate((Date)map.get("createDate"));
                User u = new User();
                u.setNickname((String) map.get("nickname"));
                u.setId((int)map.get("uid"));
                msg.setAuthor(u);
                data.add(msg);
            }
        }
        pager.setDatas(data);
        return pager;
    }
}
