package doc.dao;

import doc.dto.Pager;
import doc.dto.SystemContext;
import doc.entity.Department;
import doc.entity.Message;
import doc.entity.User;
import doc.entity.UserMessage;
import doc.util.ActionUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;

import java.util.*;

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
    public Message loadSend(int id) {
        return loadMsg(id);
    }

    private Message loadMsg(int id) {
        String sql = "SELECT msg.*, author.*, adep.*, attach.*, mattach.*, um.*, ruser.*, rdep.* FROM t_msg as msg " +
                "INNER JOIN t_user as author on msg.author_id = author.id " +
                "INNER JOIN t_dep as adep on author.dep_id = adep.id " +
                "LEFT JOIN t_msg_attach mattach on msg.id = mattach.msg_id " +
                "LEFT JOIN t_attach as attach on mattach.attach_id = attach.id " +
                "LEFT JOIN t_user_msg as um on msg.id = um.m_id " +
                "LEFT JOIN t_user as ruser on um.u_id = ruser.id " +
                "LEFT JOIN t_dep AS rdep on ruser.dep_id = rdep.id " +
                "where msg.id = :id";
        List list = getSession().createSQLQuery(sql)
                .addEntity("msg", Message.class)
                .addJoin("author", "msg.author")
                .addJoin("attach", "msg.attachments")
                .addJoin("um", "msg.receives")
                .addEntity("msg", Message.class)
                .setResultTransformer(Criteria.ROOT_ENTITY)
                .setParameter("id", id)
                .list();
        System.out.println(1);
        Message m = (Message) list.get(0);
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
        return findMsg(params, pageOffset, "send");
    }

    @Override
    public Pager<Message> findReceiveMsg(Map<String, Object> params, int pageOffset) {
        return findMsg(params, pageOffset, "receive");
    }

    private Pager<Message> findMsg(Map<String, Object> params, int pageOffset, String type) {
        String           fromuser = ActionUtil.getMapValStr(params, "fromuser");
        String           touser   = ActionUtil.getMapValStr(params, "touser");
        String           cons     = ActionUtil.getMapValStr(params, "cons");
        String           attach   = ActionUtil.getMapValStr(params, "attach");
        String           read     = ActionUtil.getMapValStr(params, "read");
        DetachedCriteria query    = DetachedCriteria.forClass(Message.class, "msg");

        if (type.equals("receive")) {
            query.createAlias("msg.receives", "um", JoinType.INNER_JOIN)
                    .createAlias("msg.author", "fromuser", JoinType.INNER_JOIN)
                    .createAlias("fromuser.dep", "dep", JoinType.INNER_JOIN);
        }
        if (!ActionUtil.isNullStr(fromuser)) {
            if (type.equals("send")) {
                query.add(Restrictions.eq("msg.deleted", false))
                        .add(Restrictions.eq("msg.author.id", Integer.parseInt(fromuser)));
            } else {
                query.add(Restrictions.like("fromuser.nickname", "%" + fromuser + "%"));
            }
        }
        if (!ActionUtil.isNullStr(touser)) {
            if (type.equals("send")) {
                query.createAlias("msg.receives", "um", JoinType.INNER_JOIN)
                        .createAlias("um.user", "touser", JoinType.INNER_JOIN)
                        .add(Restrictions.like("touser.nickname", "%" + touser + "%"));
            } else {
                query.add(Restrictions.eq("um.deleted", false))
                        .add(Restrictions.eq("um.user.id", Integer.parseInt(touser)));
            }
        }
        if (!ActionUtil.isNullStr(cons)) {
            query.add(Restrictions.disjunction()
                              .add(Restrictions.like("msg.content", "%" + cons + "%"))
                              .add(Restrictions.like("msg.title", "%" + cons + "%"))
            );
        }
        if (!ActionUtil.isNullStr(attach)) {
            query.createAlias("msg.attachments", "atts", JoinType.LEFT_OUTER_JOIN)
                    .add(Restrictions.like("atts.oldName", "%" + attach + "%"));
        }
        if (type.equals("receive") && !ActionUtil.isNullStr(read) && !read.equals("all")) {
            boolean isRead = false;
            try {
                isRead = Boolean.parseBoolean(read);
                query.add(Restrictions.eq("um.read", isRead));
            } catch (Exception e) {
                // e.printStackTrace();
            }
        }

        Pager<Message> pager    = new Pager<>();
        int            pageSize = SystemContext.getPageSize();
        // get all number
        query.setProjection(Projections.countDistinct("msg.id"));
        int count  = ((Long) query.getExecutableCriteria(getSession()).uniqueResult()).intValue();
        int offSet = getOffset(pageOffset, count, pager);

        query.setProjection(null);
        ProjectionList projList = Projections.projectionList();
        projList.add(Projections.distinct(Projections.property("msg.title")), "title");
        projList.add(Projections.property("msg.createDate"), "createDate");
        projList.add(Projections.property("msg.id"), "id");
        if (type.equals("receive")) {
            projList.add(Projections.property("fromuser.nickname"), "nickname");
            projList.add(Projections.property("fromuser.id"), "uid");
            projList.add(Projections.property("dep.name"), "depname");
            projList.add(Projections.property("um.read"), "read");
        }
        query.setProjection(projList);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.addOrder(Order.desc("msg.createDate"));
        List results = query.getExecutableCriteria(getSession())
                .setFirstResult(offSet).setMaxResults(pageSize).list();
        List<Message> data = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Map<String, Object> map = (Map) results.get(i);
            Message             msg = new Message();
            msg.setTitle((String) map.get("title"));
            msg.setId((int) map.get("id"));
            msg.setCreateDate((Date) map.get("createDate"));
            if (type.equals("receive")) {
                UserMessage um = new UserMessage();
                um.setRead((Boolean) map.get("read"));
                Set<UserMessage> ums = new LinkedHashSet<>();
                ums.add(um);
                Department dep = new Department();
                dep.setName((String)map.get("depname"));
                User u = new User();
                u.setDep(dep);
                u.setNickname((String) map.get("nickname"));
                u.setId((int) map.get("uid"));
                msg.setAuthor(u);
                msg.setReceives(ums);
            }
            data.add(msg);
        }
        pager.setDatas(data);
        return pager;
    }

    private Pager<Message> findMsg2(Map<String, Object> params, int pageOffset) {
        ProjectionList projList = Projections.projectionList();
        projList.add(Projections.distinct(Projections.property("msg.title")), "title");
        projList.add(Projections.property("msg.createDate"), "createDate");
        projList.add(Projections.property("msg.id"), "id");
        projList.add(Projections.property("msg.author"), "author");
        DetachedCriteria query = DetachedCriteria.forClass(UserMessage.class)
                .createAlias("message", "msg", JoinType.INNER_JOIN)
                .createAlias("user", "tu", JoinType.INNER_JOIN)
                .createAlias("msg.author", "msg.author", JoinType.INNER_JOIN)
                .createAlias("msg.attachments", "atts", JoinType.LEFT_OUTER_JOIN)
                /*.setFetchMode("msg", FetchMode.JOIN)
                .setFetchMode("tu", FetchMode.JOIN)
                .setFetchMode("msg.author", FetchMode.JOIN)
                .setFetchMode("atts", FetchMode.JOIN)*/
                .setProjection(projList)
                .add(Restrictions.eq("send", false))
                .add(Restrictions.eq("tu.id", 176))
                .add(Restrictions.disjunction()
                             .add(Restrictions.like("msg.content", "%标题%"))
                             .add(Restrictions.like("msg.title", "%标题%"))
                )
//                .add(Restrictions.like("atts.oldName", "%.jpg%"))
//                .add(Restrictions.like("su.nickname", "%无敌%"))
//                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
//                .setResultTransformer(Transformers.aliasToBean(Message.class))
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .addOrder(Order.desc("msg.createDate"));
        List list = query.getExecutableCriteria(getSession()).list();
/*
        for (int i = 0; i < list.size(); i++) {
            Message msg = (Message) list.get(i);
            System.out.println(msg.getId() + "----------" + msg.getTitle());
            System.out.println("user id -----" + msg.getAuthor().getId() + "-------user nickname" +  msg.getAuthor().getNickname());

        }*/
       /* for (int i = 0; i < list.size(); i++) {
            UserMessage um = (UserMessage) list.get(i);
            Message msg = um.getMessage();
            System.out.println(msg.getId() + "----------" + msg.getTitle());
            System.out.println("user nickname" + um.getMessage().getAuthor().getNickname());

        }*/

        return null;
    }

    private Pager<Message> findMsgSQL(Map<String, Object> params, int pageOffset, String select) {
        List<String> whereClause = new ArrayList<>();
        String       where       = "";
        String       type        = (String) params.get("type");
        String       fromuser    = (String) params.get("fromuser");
        String       touser      = (String) params.get("touser");
        String       cons        = (String) params.get("cons");
        String       attach      = (String) params.get("attach");

        // 发信人检索
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
        // 收信人检索
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
        // 附件检索
        if (!ActionUtil.isNullStr(attach)) {
            select += " inner join t_msg_attach ma1 on m.id = ma1.msg_id" +
                    " inner join t_attach ta1 on ma1.attach_id = ta1.id";
            where = " and ta1.old_name like :attach";
            whereClause.add(where);
        } else {
            params.remove("attach");
        }
        // 信息标题内容检索
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

        }
        // receive需要列出发信人，所有现在我只能想到这个办法，没有更好的办法了
        else {
            List list = query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                    .list();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = (Map) list.get(i);
                Message             msg = new Message();
                msg.setTitle((String) map.get("title"));
                msg.setId((int) map.get("id"));
                msg.setCreateDate((Date) map.get("createDate"));
                User u = new User();
                u.setNickname((String) map.get("nickname"));
                u.setId((int) map.get("uid"));
                msg.setAuthor(u);
                data.add(msg);
            }
        }
        pager.setDatas(data);
        return pager;
    }


}
