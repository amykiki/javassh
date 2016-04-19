package ch06.many2one;

import javax.persistence.*;

/**
 * Created by Amysue on 2016/4/19.
 */
@Entity
@Table(name = "t_address9")
public class Address9 {
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

    @ManyToOne(targetEntity = Person9.class)
    @JoinColumn(name = "person_id", referencedColumnName = "p_id", nullable = false)
    private Person9 person;

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public Person9 getPerson() {
        return person;
    }

    public void setPerson(Person9 person) {
        this.person = person;
    }
}
