package ch06.one2many;

import ch06.one2many.Address8;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Amysue on 2016/4/19.
 */
@Entity
@Table(name = "t_person8")
public class Person8 {
    @Id
    @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int age;
    @OneToMany(targetEntity = Address8.class)
    @JoinTable(name = "person_address_8",
               joinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "p_id")},
               inverseJoinColumns = {@JoinColumn(name = "address_id", referencedColumnName = "a_id", unique = true)}
    )
    private Set<Address8> address = new HashSet<>();

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

    public Set<Address8> getAddress() {
        return address;
    }

    public void setAddress(Set<Address8> address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
