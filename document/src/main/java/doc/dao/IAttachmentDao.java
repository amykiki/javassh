package doc.dao;

import doc.entity.Attachment;

import java.util.List;

/**
 * Created by Amysue on 2016/5/13.
 */
public interface IAttachmentDao extends IBaseDao<Attachment> {
    public void deleteAttachments(List<Integer> aIds);
}
