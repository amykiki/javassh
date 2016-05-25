package org.mybatis.smvc.entity;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Amysue on 2016/5/25.
 */
public class Message implements Serializable{

    private static final long serialVersionUID = 1837557715523579729L;
    private int id;
    private String title;
    private String content;
    private Date createDate;
    private User author;
    private boolean deleted;
    private List<Attachment> attachments = new ArrayList<>();
    private List<UserMessage> receives    = new ArrayList<>();

    public List<UserMessage> getReceives() {
        return receives;
    }

    public void setReceives(List<UserMessage> receives) {
        this.receives = receives;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
