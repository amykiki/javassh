package org.mybatis.smvc.entity;

import java.io.Serializable;

/**
 * Created by Amysue on 2016/5/25.
 */
public class UserMessage implements Serializable{
    private static final long serialVersionUID = 1160412446121922783L;

    private int id;
    private User    user;
    private Message message;
    private boolean read;
    private boolean deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
