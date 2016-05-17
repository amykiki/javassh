package doc.dao;

import doc.entity.UserMessage;
import org.springframework.stereotype.Repository;

/**
 * Created by Amysue on 2016/5/17.
 */
@Repository("userMsgDao")
public class UserMessageDao extends BaseDao<UserMessage> implements IUserMessageDao {
}
