package doc.dao;

import doc.entity.User;
import doc.enums.Role;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by Amysue on 2016/5/7.
 */
public interface IUserDao extends IBaseDao<User> {
    public List findByCriteria(DetachedCriteria query);

    public int getUserNum(int depId);

    public void updateRole(Role role, int uId);
}
