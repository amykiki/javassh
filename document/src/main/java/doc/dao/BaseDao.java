package doc.dao;

import doc.dto.*;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Amysue on 2016/5/2.
 */
public class BaseDao<T> implements IBaseDao<T>{
    private SessionFactory sessionFactory;
    private Class<T>       persistentClass;

    public BaseDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Type              type              = genericSuperclass.getActualTypeArguments()[0];
        if (type instanceof Class) {
            this.persistentClass = (Class<T>) type;
        } else if (type instanceof ParameterizedType) {
            this.persistentClass = (Class<T>) ((ParameterizedType)type).getRawType();
        }
//        persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Class<T> getPersistentClass() {

        return persistentClass;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(T t) {
        getSession().save(t);

    }

    @Override
    public void delete(int id) {
        T t = get(id);
        if (t != null) {
            getSession().delete(t);
        }
       /* T t = load(id);
        getSession().delete(t);*/
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public T load(int id) {
        return (T) getSession().load(getPersistentClass(), id);
    }

    @Override
    public T get(int id) {
        return (T) getSession().get(getPersistentClass(), id);
    }

    @Override
    public List<T> list(String hql, Object... args) {
        Query query = getHQL(hql, args);
        return query.list();
    }

    @Override
    public List<Object> listObj(String hql, Object... args) {
        Query query = getHQL(hql, args);
        return query.list();
    }

    @Override
    public Pager<T> find(String hql, int pageOffset, int pageSize, Object... args) {
        Query query = getHQL(hql, args);
        int count = getCountByHQL();
        if (pageOffset < 0 || pageOffset >= count) {
            pageOffset = 0;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        query.setFirstResult(pageOffset).setMaxResults(pageSize);
        Pager<T> pager = new Pager<>();
        pager.setPageOffset(pageOffset);
        pager.setPageSize(pageSize);
        pager.setDatas(query.list());
        pager.setTotalRecords(count);
        return pager;
    }

    private int getCountByHQL() {
        return 0;
    }

    @Override
    public void executeHQL(String hql, Object... args) {
        Query query = getHQL(hql, args);
        query.executeUpdate();
    }

    @Override
    public Object queryByHQL(String hql, Object... args) {
        Query query = getHQL(hql, args);
        return query.uniqueResult();
    }

    private Query getHQL(String hql, Object... args) {
        Query query = getSession().createQuery(hql);
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                query.setParameter(i, args[i]);
            }
        }
        return query;
    }
}
