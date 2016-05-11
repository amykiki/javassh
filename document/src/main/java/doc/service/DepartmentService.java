package doc.service;

import doc.dao.IDepScopeDao;
import doc.dao.IDepartmentDao;
import doc.dao.IUserDao;
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
    private IDepScopeDao   depScopeDao;
    private IUserDao userDao;

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

    @Resource(name = "userDao")
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(Department department) throws DocException {
        if (getDepByName(department.getName()) != null) {
            throw new DocException("[" + department.getName() + "]已经创建了");
        }
        depDao.add(department);

    }

    @Override
    public void delete(int id)throws DocException {
        //// TODO: 2016/5/6  考虑如果用用户和message存在则不应删除
        int count = userDao.getUserNum(id);
        if (count > 0) {
            throw new DocException("[" + load(id).getName() + "]有" +count+ "名用户，不能删除");
        }
        depDao.delete(id);
    }

    @Override
    public Department load(int id) {
        return depDao.load(id);
    }

    @Override
    public void update(Department department) throws DocException {
        Department oDep = getDepByName(department.getName());
        if (oDep != null) {
            if (oDep.equals(department)) {
                return;
            } else {
                throw new DocException("[" + department.getName() + "]已经创建了");
            }
        }
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
        return (Department) depDao.queryByHQL(hql, name);
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
        dds.removeAll(sds);
        if (dds.size() > 0) {
            depScopeDao.deleteScope(depId, dds);
        }
        sds.removeAll(ods);
        if (sds.size() > 0) {
            for (int sId : sds) {
                depScopeDao.addDepScope(depId, sId);
            }
        }
    }

    @Override
    public List<Department> listAllDepScope(int depId) {
        String hql = "select ds.scopeDep from DepScope ds where ds.depId = ?";
        List<Department> depScopes = depDao.list(hql, depId);
        return depScopes;
    }
}
