package doc.service;

import doc.entity.DepScope;
import doc.entity.Department;
import doc.exception.DocException;

import java.util.List;

/**
 * Created by Amysue on 2016/5/4.
 */
public interface IDepartmentService {
    public void add(Department department) throws DocException;

    public void delete(int id);

    public Department load(int id);

    public void update(Department department)throws DocException;

    public List<Department> listAllDep();

    public List<Department> listAllDepScope(int depId);

    public Department getDepByName(String name);

    public void updateDepScope(int depId, Integer[] scopeDepIds);

    public void addDepScope(int depId, int scopeDepId);

    public void deleteScope(int depId, Integer[] scopeDepIds);
    public List<Integer> listDepScopeIds(int depId);


}
