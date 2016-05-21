package doc.entity;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Amysue on 2016/5/13.
 */
@Entity
@Table(name = "t_msg")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title", columnDefinition = "VARCHAR(255) CHARACTER SET utf8")
    private String title;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "is_delete", nullable = false, columnDefinition = "tinyint default false")
    private boolean deleted;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_msg_attach",
            joinColumns = @JoinColumn(name = "msg_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attach_id", referencedColumnName = "id")
    )
    private Set<Attachment> attachments = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "message")
    private Set<UserMessage> receives = new LinkedHashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
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

    public Set<UserMessage> getReceives() {
        return receives;
    }

    public void setReceives(Set<UserMessage> receives) {
        this.receives = receives;
    }

    public Message() {
    }
}
