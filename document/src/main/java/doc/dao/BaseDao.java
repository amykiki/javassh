package doc.dao;

import doc.dto.Pager;
import doc.dto.SystemContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Amysue on 2016/5/2.
 */
public class BaseDao<T> implements IBaseDao<T>{
    protected Logger logger;
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
        logger = LogManager.getLogger(persistentClass);
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
    public void delete(T t) {
        getSession().delete(t);
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
    public Pager<T> find(DetachedCriteria query, String associationPath, int pageOffset) {
        query.setProjection(Projections.rowCount());
        List list = query.getExecutableCriteria(getSession()).list();
        int count = ((Long)list.get(0)).intValue();
        Pager<T> pager = new Pager<>();
        int offSet = getOffset(pageOffset, count, pager);

        query.setProjection(null);
        query.setResultTransformer(Criteria.ROOT_ENTITY);
        if (associationPath != null) {
            query.setFetchMode(associationPath, FetchMode.JOIN);
        }
        int pageSize = SystemContext.getPageSize();
        List<T> data = query.getExecutableCriteria(getSession())
                .setFirstResult(offSet)
                .setMaxResults(pageSize)
                .list();
        pager.setDatas(data);
        return pager;
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

    @Override
    public List queryByCriteria(DetachedCriteria query) {
        return query.getExecutableCriteria(getSession()).list();
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

    protected int getOffset(int pageOffset, int count, Pager<T> pager) {
        int toPage = pageOffset;
        int pageSize = SystemContext.getPageSize();
        int pageRange = SystemContext.getPageRange();
        int begin       = 0;
        int end         = 0;
        int offSet = 0;
        int allPageNums = 0;
        if (pageSize <= 0) {
            pageSize = 10;
        }
        if (count == 0) {
            toPage = 0;
            allPageNums = 0;
        } else {
            allPageNums = (count - 1) / pageSize + 1;
            if (toPage > allPageNums) {
                toPage = allPageNums;
            } else if (toPage <= 0) {
                toPage = 1;
            }
            //get begin and end index
            begin = toPage - pageRange/ 2;
            if (begin < 1) {
                begin = 1;
            }
            end = begin - 1 + pageRange;
            if (end > allPageNums) {
                begin -= end - allPageNums;
                if (begin < 1) {
                    begin = 1;
                }
                end = allPageNums;
            }
            offSet = (toPage - 1) * pageSize;
        }
        logger.info("toPage = " + toPage);
        pager.setToPage(toPage);
        pager.setPageSize(pageSize);
        pager.setTotalRecords(count);
        pager.setBegin(begin);
        pager.setEnd(end);
        pager.setAllPageNums(allPageNums);
        return offSet;
    }
}
