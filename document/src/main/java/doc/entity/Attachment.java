package doc.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Amysue on 2016/5/13.
 */
@Entity
@Table(name = "t_attach")
public class Attachment {
    private int id;
    private String newName;
    private String oldName;
    private String contentType;
    private long size;
    private Date createDate;
    private String type;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 附件的新名称，这个名称是不会重复的，可通过UUID或者时间毫秒数获得
     * @return
     */
    @Column(name = "new_name")
    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    /**
     * 附件原名称
     * @return
     */
    @Column(name = "old_name")
    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    /**
     * 附件的文件类型
     * @return
     */
    @Column(name = "content_type")
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * 附件大小，以字节为单位
     * @return
     */
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    /**
     * 附件的后缀名
     * @return
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 附件创建时间
     * @return
     */
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
