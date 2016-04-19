package ch06.many2many;

import ch06.many2many.Person10;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Amysue on 2016/4/19.
 */
@Entity
@Table(name = "t_address10")
public class Address10 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    private int id;

    private String addressInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToMany(targetEntity = Person10.class)
    @JoinTable(name = "person_address_10",
            joinColumns = @JoinColumn(name = "address_id", referencedColumnName = "a_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "p_id"))
    private Set<Person10> persons = new HashSet<>();

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public Set<Person10> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person10> persons) {
        this.persons = persons;
    }
}
