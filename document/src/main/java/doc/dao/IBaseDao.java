package doc.dao;

import doc.dto.*;
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

    public Pager<T> find(String hql, int pageOffset, int pageSize, Object... args);

    public void executeHQL(String hql, Object... args);

    public Object queryByHQL(String hql, Object... args);

}
