package doc.service;

import doc.dto.AttachDto;
import doc.dto.Pager;
import doc.entity.Attachment;
import doc.entity.Message;
import doc.exception.DocException;

import java.util.List;
import java.util.Map;

/**
 * Created by Amysue on 2016/5/13.
 */
public interface IMessageService {
    public void add(Message msg, List<Integer> sendToIds, AttachDto attachDto);

    public void add(Message msg, Attachment attachment);
    public void add(Message msg);

    public void delete(int id);

    public Message load(int id);

    public Message loadEagerById(int id);

    public Pager<Message> findSendMsg(Map<String, Object> params, int pageOffset);

    public Pager<Message> findReceiveMsg(Map<String, Object> params, int pageOffset);

    public Message loadSendMsg(int id) throws DocException;
    public Message updateReceiveMsg(int id) throws DocException;

}
