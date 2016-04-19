package ch05.componetCollection;

import ch05.embeddable.Person3;
import org.hibernate.annotations.CollectionType;
import org.hibernate.annotations.Parent;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amysue on 2016/4/19.
 */
@Embeddable
public class NameCollection {

    @Column(name = "p_firstname", nullable = false)
    private String first;
    @Column(name = "p_lastname", nullable = false)
    private String last;
    @Parent
    private Person4 owner;

    @ElementCollection(targetClass = Integer.class)
    @CollectionTable(name = "t_power", joinColumns = @JoinColumn(name = "person_name_id", nullable = false))
    @MapKeyColumn(name = "name_aspect")
    @MapKeyClass(String.class)
    @Column(name = "name_power", nullable = false)
    private Map<String, Integer> power = new HashMap<>();



    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Person4 getOwner() {
        return owner;
    }

    public void setOwner(Person4 owner) {
        this.owner = owner;
    }

    public Map<String, Integer> getPower() {
        return power;
    }

    public void setPower(Map<String, Integer> power) {
        this.power = power;
    }
}
