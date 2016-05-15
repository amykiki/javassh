package doc.service;

import doc.entity.Attachment;

import java.util.List;

/**
 * Created by Amysue on 2016/5/13.
 */
public interface IAttachmentService {
    public void add(Attachment attachment);

    public void deleteAttachments(List<Integer> aIds);
}
