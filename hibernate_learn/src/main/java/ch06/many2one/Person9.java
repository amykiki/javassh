package ch06.many2one;

import ch06.many2one.Address9;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Amysue on 2016/4/19.
 */
@Entity
@Table(name = "t_person9")
public class Person9 {
    @Id
    @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int age;
    @OneToMany(targetEntity = Address9.class, mappedBy = "person")
    private Set<Address9> address = new HashSet<>();

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

    public Set<Address9> getAddress() {
        return address;
    }

    public void setAddress(Set<Address9> address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
