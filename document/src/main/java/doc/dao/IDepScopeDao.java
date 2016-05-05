package doc.dao;

import doc.entity.DepScope;

import java.util.List;

/**
 * Created by Amysue on 2016/5/5.
 */
public interface IDepScopeDao extends IBaseDao<DepScope> {
    public void addDepScope(int depId, int scopeDepId);

    public void deleteScope(int depId, List<Integer> scopeDepIds);

    public List<DepScope> listDepScope(int depId);
    public List<Integer> listDepScopeIds(int depId);
}
