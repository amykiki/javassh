package doc.dao;

import doc.dto.*;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by Amysue on 2016/5/2.
 */
public interface IBaseDao<T> {
    public void add(T t);

    public void delete(int id);

    public void update(T t);

    public T get(int id);
    public T load(int id);

    public List<T> list(String hql, Object... args);
    public List<Object> listObj(String hql, Object... args);

    public Pager<T> find(DetachedCriteria query, String associationPath, int pageOffset);

    public void executeHQL(String hql, Object... args);

    public Object queryByHQL(String hql, Object... args);

    public List queryByCriteria(DetachedCriteria query);

}
