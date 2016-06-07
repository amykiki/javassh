package org.mybatis.smvc.entity;

import org.mybatis.smvc.enums.Role;

import javax.validation.constraints.NotNull;

/**
 * Created by Amysue on 2016/6/6.
 */
public class UserFind {
    private String username;
    private String nickname;
    private String depname;
    @NotNull
    private Role role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
