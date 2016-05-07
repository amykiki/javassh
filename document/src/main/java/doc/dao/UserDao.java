package doc.dao;

import doc.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Amysue on 2016/5/7.
 */
@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao{
}
