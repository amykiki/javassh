package doc.service;

import doc.entity.Attachment;
import doc.entity.Message;

import java.util.List;

/**
 * Created by Amysue on 2016/5/13.
 */
public interface IMessageService {
    public void add(Message msg, List<Attachment>attachments);

    public void add(Message msg, Attachment attachment);
    public void add(Message msg);

    public void delete(int id);

    public Message load(int id);

    public Message loadEagerById(int id);
}
