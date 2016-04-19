package ch06.many2many;

import ch06.many2many.Address10;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Amysue on 2016/4/19.
 */
@Entity
@Table(name = "t_person10")
public class Person10 {
    @Id
    @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int age;

    @ManyToMany(targetEntity = Address10.class)
    @JoinTable(name = "person_address_10",
               joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "p_id"),
               inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "a_id"))

    private Set<Address10> address = new HashSet<>();

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

    public Set<Address10> getAddress() {
        return address;
    }

    public void setAddress(Set<Address10> address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
