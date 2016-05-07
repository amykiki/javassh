package doc.service;

import doc.dao.IDepartmentDao;
import doc.dao.IUserDao;
import doc.dto.Pager;
import doc.entity.Department;
import doc.entity.User;
import doc.exception.DocException;
import doc.util.ActionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Amysue on 2016/5/7.
 */
@Service("userService")
public class UserService implements IUserService {
    private IUserDao userDao;
    private IDepartmentDao depDao;

    @Resource(name = "userDao")
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Resource(name = "depDao")
    public void setDepDao(IDepartmentDao depDao) {
        this.depDao = depDao;
    }

    @Override
    public void add(User user, int depId) throws DocException {
        Department department = depDao.get(depId);
        if (department == null) {
            throw new DocException("不存在的部门");
        }
        if (loadLazyByUsername(user.getUsername()) != null) {
            throw new DocException("[" + user.getUsername() + "]已经存在");
        }
        user.setDep(department);
        userDao.add(user);
    }

    @Override
    public void delete(int id) {
        // TODO: 2016/5/7  删除用户所发信息和公文
        userDao.delete(id);
    }

    @Override
    public User loadLazyByUsername(String username) {
        String hql = "select u from User u where u.username = ?";
        return (User)userDao.queryByHQL(hql, username);
    }

    @Override
    public User loadEagerByUsername(String username) {
        String hql = "select u from User u left join fetch u.dep where u.username = ?";
        return (User)userDao.queryByHQL(hql, username);
    }

    @Override
    public User loadLazyById(int id) {
        String hql = "select u from User u where u.id = ?";
        return (User)userDao.queryByHQL(hql, id);
    }

    @Override
    public User loadEagerById(int id) {
        String hql = "select u from User u left join fetch u.dep where u.id = ?";
        return (User)userDao.queryByHQL(hql, id);
    }

    @Override
    public User load(int id) {
//        String hql = "select u from User u left join fetch u.dep where u.id = ?";
//        return (User)userDao.queryByHQL(hql, id);
        return userDao.load(id);
    }

    @Override
    public void update(User user, int depId) throws DocException {
        Department department = depDao.get(depId);
        if (department == null) {
            throw new DocException("不存在的部门");
        }
        User oldUser = loadLazyByUsername(user.getUsername());
        if (oldUser != null && oldUser.getId() != user.getId()) {
            throw new DocException("[" + user.getUsername() + "]已经存在");
        }
        user.setDep(department);
        ActionUtil.copyNotNullProperties(oldUser, user);
    }

    /*@Override
    public void update(User user) throws DocException{
        User oldUser = loadEagerById(user.getId());
        String newUsername = user.getUsername();
        if (newUsername != null && !newUsername.equals(oldUser.getUsername())) {
            if (loadLazyByUsername(newUsername) != null) {
                throw new DocException("[" + user.getUsername() + "]已经存在");
            }
        }
        ActionUtil.copyNotNullProperties(oldUser, user);
//        不再需要调用update
//        userDao.update(user);
    }*/

    @Override
    public Pager<User> findUser(Map<String, Object> params) {
        return null;
    }
}
