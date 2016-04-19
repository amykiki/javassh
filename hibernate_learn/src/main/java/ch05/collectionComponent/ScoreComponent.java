package ch05.collectionComponent;

import org.hibernate.annotations.Parent;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Amysue on 2016/4/19.
 */
@Embeddable
public class ScoreComponent {
    @Column(name = "score_level")
    private String  level;
    @Column(name = "score_mark")
    private int  mark;
    @Parent
    private Person5 owner;

    public ScoreComponent(String level, int score) {
        this.level = level;
        this.mark = score;
    }

    public ScoreComponent() {
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Person5 getOwner() {
        return owner;
    }

    public void setOwner(Person5 owner) {
        this.owner = owner;
    }
}
