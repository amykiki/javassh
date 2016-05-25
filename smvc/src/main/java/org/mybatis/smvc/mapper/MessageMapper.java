package org.mybatis.smvc.mapper;

import org.mybatis.smvc.entity.Message;

/**
 * Created by Amysue on 2016/5/25.
 */
public interface MessageMapper {
    public Message load(int id);
}
