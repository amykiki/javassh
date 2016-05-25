package org.mybatis.smvc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Amysue on 2016/5/25.
 */
public class Attachment implements Serializable {
    private static final long serialVersionUID = 3510896970200547709L;
    private int    id;
    private String newName;
    private String oldName;
    private String contentType;
    private long   size;
    private Date   createDate;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
