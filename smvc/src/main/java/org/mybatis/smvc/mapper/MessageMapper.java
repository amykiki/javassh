package org.mybatis.smvc.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.smvc.entity.Message;

import java.util.List;
import java.util.Map;

/**
 * Created by Amysue on 2016/5/25.
 */
public interface MessageMapper {
    public Message load(int id);

    public List<Message> findSend(@Param("msg") Map<String, Object> fParams);
}
