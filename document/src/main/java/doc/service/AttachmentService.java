package doc.service;

import doc.dao.IAttachmentDao;
import doc.entity.Attachment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Amysue on 2016/5/13.
 */
@Service("attachService")
public class AttachmentService implements IAttachmentService{
    private IAttachmentDao attachDao;

    @Resource(name = "attachDao")
    public void setAttachDao(IAttachmentDao attachDao) {
        this.attachDao = attachDao;
    }

    @Override
    public void add(Attachment attachment) {
        attachDao.add(attachment);
    }

    @Override
    public void deleteAttachments(List<Integer> aIds) {
        attachDao.deleteAttachments(aIds);
    }
}
