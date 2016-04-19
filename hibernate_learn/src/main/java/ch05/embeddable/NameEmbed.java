package ch05.embeddable;

import org.hibernate.annotations.Parent;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Amysue on 2016/4/19.
 */
@Embeddable
public class NameEmbed {

    @Column(name = "p_firstname", nullable = false)
    private String first;
    @Column(name = "p_lastname", nullable = false)
    private String last;
    @Parent
    private Person3 owner;

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

    public Person3 getOwner() {
        return owner;
    }

    public void setOwner(Person3 owner) {
        this.owner = owner;
    }
}
