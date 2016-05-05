package doc.dao;

import doc.entity.DepScope;
import doc.entity.Department;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Amysue on 2016/5/5.
 */
@Repository("depScopeDao")
public class DepScopeDao extends BaseDao<DepScope> implements IDepScopeDao {
    @Override
    public void addDepScope(int depId, int scopeDepId) {
        String hql = "select ds from DepScope ds where ds.depId = ? and ds.scopeDep.id = ?";
        DepScope ds = (DepScope) queryByHQL(hql, depId, scopeDepId);
        if (ds != null) return;
        ds = new DepScope();
        Department scopeDep = new Department();
        scopeDep.setId(scopeDepId);
        ds.setDepId(depId);
        ds.setScopeDep(scopeDep);
        add(ds);
    }

    @Override
    public void deleteScope(int depId, List<Integer> scopeDepIds) {
        String hql = "delete DepScope ds where ds.depId = :dId and ds.scopeDep.id IN (:scopeIds)";
        Query query = getSession().createQuery(hql);
        query.setParameter("dId", depId);
        query.setParameterList("scopeIds", scopeDepIds);
        query.executeUpdate();
    }

    @Override
    public List<DepScope> listDepScope(int depId) {
        String hql = "select ds from DepScope ds where ds.depId = ?";
        return list(hql, depId);
    }

    @Override
    public List<Integer> listDepScopeIds(int depId) {
        String hql = "select ds.scopeDep.id from DepScope ds where ds.depId = ?";
        List<Integer> depScopeIds = (List)listObj(hql, depId);
        return depScopeIds;
    }
}
