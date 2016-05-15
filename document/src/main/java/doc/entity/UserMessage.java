package doc.entity;

import javax.persistence.*;

/**
 * Created by Amysue on 2016/5/13.
 */
@Entity
@Table(name = "t_user_msg")
public class UserMessage {
    private int id;
    private User user;
    private Message message;
    private boolean read;
    private boolean send;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_id")
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Column(name = "is_read", nullable = false, columnDefinition = "tinyint default false")
    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Column(name = "is_send", nullable = false, columnDefinition = "tinyint default false")
    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }
}
