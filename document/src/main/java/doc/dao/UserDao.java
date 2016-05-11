package doc.dao;

import doc.entity.User;
import doc.enums.Role;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Amysue on 2016/5/7.
 */
@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao{
    @Override
    public int getUserNum(int depId) {
        DetachedCriteria query = DetachedCriteria.forClass(User.class);
        query.add(Restrictions.eq("dep.id", depId));
        query.setProjection(Projections.rowCount());
        int count = ((Long)queryByCriteria(query).get(0)).intValue();
        return count;
    }

    @Override
    public void updateRole(Role role, int uId) {
        String hql = "update User set role = ? where id = ?";
        executeHQL(hql, role, uId);
    }

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
