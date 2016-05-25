package org.mybatis.smvc.entity;

import org.mybatis.smvc.enums.Role;

import java.io.Serializable;

/**
 * Created by Amysue on 2016/5/25.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 2879893054128289558L;
    private int        id;
    private String     username;
    private String     password;
    private String     nickname;
    private Role       role;
    private String     email;
    private Department dep;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDep() {
        return dep;
    }

    public void setDep(Department dep) {
        this.dep = dep;
    }
}
