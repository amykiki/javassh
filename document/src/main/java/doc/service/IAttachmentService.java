package doc.service;

import doc.dto.AttachDto;
import doc.entity.Attachment;

import java.util.List;
import java.util.Set;

/**
 * Created by Amysue on 2016/5/13.
 */
public interface IAttachmentService {
    public Set<Attachment> add(AttachDto attachDto);

    public void deleteAttachments(List<Integer> aIds);
}
