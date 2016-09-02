package com.spring.mvc.rest.dao;

import java.util.List;

/**
 * Created by Amysue on 2016/9/2.
 */
public interface BaseDao<T> {
    public void add(T t);

    public void delete(int id);

    public void delete(T t);

    public void update(T t);

    public T get(int id);

    public T load(int id);

    public List<T> list(String hql, Object...args);
}
