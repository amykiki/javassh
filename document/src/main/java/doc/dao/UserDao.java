package doc.dao;

import doc.entity.User;
import org.hibernate.FetchMode;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Amysue on 2016/5/7.
 */
@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao{
    @Override
    public List findByCriteria(DetachedCriteria query) {
 /*       List list = getSession().createCriteria(User.class)
                .add(Restrictions.like("nickname", "%王%"))
//                .createAlias("dep", "dep", JoinType.LEFT_OUTER_JOIN)
//                .add(Restrictions.eq("dep.id", 3))
//                .add(Restrictions.gt("id", 10))
                .setFetchMode("dep", FetchMode.JOIN)
//                .setFirstResult(0)
//                .setMaxResults(10)
                .list();*/
        List list = getSession().createCriteria(User.class)
             .add(Subqueries.propertyIn("dep.id", query))
                .list();
        return list;

       /* List list = getSession().createCriteria(User.class, "u")
                .createAlias("dep", "dep")
                .setProjection(Projections.projectionList()
                        .add(Projections.rowCount())
                        .add(Projections.groupProperty("dep.name"))
                )
//                .add(Restrictions.like("nickname", "%王%"))
                .list();

        return list;*/
    }
}
