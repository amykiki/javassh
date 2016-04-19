package dao;

import ch05.list.Person1;
import model.IBaseModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import uitl.HibernateUtil;

/**
 * Created by Amysue on 2016/4/19.
 */
public class HibernateDao<T extends IBaseModel> {
    Session session;
    Transaction tx;

    public int add(T p) {
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(p);
            tx.commit();
            return p.getId();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }
        return 0;
    }

    public Object load(Class clz, int index) {
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Object p = session.load(clz, index);
            return p;
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }
        return null;
    }
}
