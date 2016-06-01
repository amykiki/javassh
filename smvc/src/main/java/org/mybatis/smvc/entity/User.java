package org.mybatis.smvc.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.mybatis.smvc.enums.Role;
import org.mybatis.smvc.validators.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by Amysue on 2016/5/25.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 2879893054128289558L;
    private int        id;

    @Pattern(regexp = "[a-zA-Z]{4}[a-zA-Z0-9]{1,6}", message = "用户名前四位必须为字母，只能包含字母和数字", groups = {Add.class, UpdatePwd.class})
    private String username;

    @Length(min = 4, max = 10, message = "密码长度必须在4-10之间", groups = {Add.class, UpdatePwd.class})
    private String     password;

    @NotEmpty(message = "昵称不能为空", groups = {Add.class, Update.class})
    @Forbidden(value = {"admin", "管理员"}, groups = {Add.class, Update.class})
    private String     nickname;

    private Role       role;

    @NotEmpty(message = "邮箱不能为空",groups = {Add.class, Update.class} )
    @Email(message = "邮箱格式不正确", groups = {Add.class, Update.class})
    private String     email;

    @ValidUserDep(groups = {Add.class, Update.class})
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", dep=" + dep +
                '}';
    }
}
