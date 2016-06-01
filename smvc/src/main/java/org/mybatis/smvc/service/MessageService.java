package org.mybatis.smvc.service;

import org.mybatis.smvc.entity.Message;
import org.mybatis.smvc.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Amysue on 2016/5/27.
 */
@Service("msgService")
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    public Message load(int id) {
        return messageMapper.load(id);
    }
    public List<Message> findSend(Map<String, Object> fParams) {
        return messageMapper.findSend(fParams);
    }

}
