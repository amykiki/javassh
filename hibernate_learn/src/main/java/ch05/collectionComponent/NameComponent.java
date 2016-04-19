package ch05.collectionComponent;

import ch05.enumerated.Person;
import org.hibernate.annotations.Parent;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Amysue on 2016/4/19.
 */
@Embeddable
public class NameComponent {
    @Column(name = "p_firstname")
    private String first;
    @Column(name = "p_lastname")
    private String last;
    @Parent
    private Person5 owner;

    public NameComponent() {
    }

    public NameComponent(String first, String last) {
        this.first = first;
        this.last = last;
    }

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

    public Person5 getOwner() {
        return owner;
    }

    public void setOwner(Person5 owner) {
        this.owner = owner;
    }
}
