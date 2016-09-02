package com.spring.mvc.rest.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Amysue on 2016/9/2.
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
    protected Logger       log4j;
    private SessionFactory sessionFactory;
    private Class<T>       persistentClass;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    public BaseDaoImpl() {
        ParameterizedType superType = (ParameterizedType) getClass().getGenericSuperclass();
        Type type = superType.getActualTypeArguments()[0];
        if (type instanceof Class) {
            persistentClass = (Class<T>) type;
        } else if (type instanceof ParameterizedType) {
            persistentClass = (Class<T>)((ParameterizedType)type).getRawType();
        }
        log4j = LogManager.getLogger(persistentClass);
    }

    @Override
    public void add(T t) {
        getSession().persist(t);
    }

    @Override
    public void delete(int id) {
        T t = get(id);
        if (t != null) {
            getSession().delete(t);
        }
    }

    @Override
    public void delete(T t) {
        getSession().delete(t);
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public T get(int id) {
        return (T)getSession().get(persistentClass, id);
    }

    @Override
    public T load(int id) {
        return (T) getSession().load(persistentClass, id);
    }

    @Override
    public List<T> list(String hql, Object... args) {
        Query query = getHQL(hql, args);
        return query.list();
    }

    private Query getHQL(String hql, Object... args) {
        Query query = getSession().createQuery(hql);
        if (args != null) {
            for(int i = 0; i < args.length; i++) {
                query.setParameter(i, args[i]);
            }
        }
        return query;
    }
}
