package org.mybatis.smvc.entity;

/**
 * Created by Amysue on 2016/6/20.
 */
public class UserPsw {
    private int id;
    private String username;
    private String password;
    private String salt;

    public UserPsw() {
    }

    public UserPsw(String password, String salt) {
        this.password = password;
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
