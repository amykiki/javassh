package doc.dao;

import doc.entity.Attachment;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Amysue on 2016/5/13.
 */
@Repository("attachDao")
public class AttachmentDao extends BaseDao<Attachment> implements IAttachmentDao {
    @Override
    public void deleteAttachments(List<Integer> aIds) {
        String hql = "delete Attachment attach where attach.id IN (:Ids)";
        Query query = getSession().createQuery(hql);
        query.setParameterList("Ids", aIds);
        query.executeUpdate();
    }
}
