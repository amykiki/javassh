package ch06.one2one;

import ch06.one2one.Address7;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

/**
 * Created by Amysue on 2016/4/19.
 */
@Entity
@Table(name = "t_person7")
public class Person7 {
    @Id
    @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int age;
    @OneToOne(targetEntity = Address7.class)
    @JoinColumn(name = "address_id", referencedColumnName = "a_id", nullable = false, unique = true)
//    @JoinColumn(name = "address_id", nullable = false, unique = true)
    @Cascade(CascadeType.ALL)
    private Address7 address;

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

    public Address7 getAddress() {
        return address;
    }

    public void setAddress(Address7 address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
