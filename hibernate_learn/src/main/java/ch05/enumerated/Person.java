package ch05.enumerated;

import enums.Role;

import javax.persistence.*;

/**
 * Created by Amysue on 2016/4/18.
 */
@Entity
@Table(name = "t_person_enum")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String name;
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "Integer(3)")
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
