package ch06.many2one;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.lang.annotation.Target;

/**
 * Created by Amysue on 2016/4/19.
 */
@Entity
@Table(name = "t_person6")
public class Person6 {
    @Id
    @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int age;
    @ManyToOne(targetEntity = Address6.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    @Cascade(CascadeType.ALL)
    private Address6 address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address6 getAddress() {
        return address;
    }

    public void setAddress(Address6 address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
