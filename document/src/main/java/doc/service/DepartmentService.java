package doc.service;

import doc.dao.IDepScopeDao;
import doc.dao.IDepartmentDao;
import doc.entity.DepScope;
import doc.entity.Department;
import doc.exception.DocException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Amysue on 2016/5/4.
 */
@Service("depService")
public class DepartmentService implements IDepartmentService {
    private IDepartmentDao depDao;
    private IDepScopeDao depScopeDao;

    public IDepartmentDao getDepDao() {
        return depDao;
    }

    @Resource(name = "depDao")
    public void setDepDao(IDepartmentDao depDao) {
        this.depDao = depDao;
    }

    public IDepScopeDao getDepScopeDao() {
        return depScopeDao;
    }

    @Resource(name = "depScopeDao")
    public void setDepScopeDao(IDepScopeDao depScopeDao) {
        this.depScopeDao = depScopeDao;
    }

    @Override
    public void add(Department department) throws DocException{
        if (getDepByName(department.getName()) != null) {
            throw new DocException("[" + department.getName() + "]已经创建了");
        }
        depDao.add(department);

    }

    @Override
    public void delete(int id) {
        depDao.delete(id);
    }

    @Override
    public Department load(int id) {
        return depDao.load(id);
    }

    @Override
    public void update(Department department) {
        depDao.update(department);
    }

    @Override
    public List<Department> listAllDep() {
        String hql = "select dep from Department dep";
        return depDao.list(hql);
    }

    @Override
    public Department getDepByName(String name) {
        String hql = "from Department where name = ?";
        return (Department)depDao.queryByHQL(hql, name);
    }

    @Override
    public void addDepScope(int depId, int scopeDepId) {
        depScopeDao.addDepScope(depId, scopeDepId);
    }

    @Override
    public void deleteScope(int depId, Integer[] scopeDepIds) {
        depScopeDao.deleteScope(depId, Arrays.asList(scopeDepIds));
    }

    @Override
    public List<Integer> listDepScopeIds(int depId) {
        return depScopeDao.listDepScopeIds(depId);
    }

    @Override
    public void updateDepScope(int depId, Integer[] scopeDepIds) {
        List<Integer> ods = depScopeDao.listDepScopeIds(depId);
        List<Integer> sds = new ArrayList<>(Arrays.asList(scopeDepIds));
        List<Integer> dds = new ArrayList<>(ods);
        if (dds.removeAll(sds)) {
            depScopeDao.deleteScope(depId, dds);
        }
        if (sds.removeAll(ods)) {
            for (int sId : sds) {
                depScopeDao.addDepScope(depId, sId);
            }
        }
    }
}
