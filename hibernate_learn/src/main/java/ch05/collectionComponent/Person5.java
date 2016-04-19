package ch05.collectionComponent;

import model.IBaseModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Amysue on 2016/4/19.
 */
@Entity
@Table (name = "t_person_collection")
public class Person5 implements IBaseModel{
    @Id @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int age;
    @ElementCollection(targetClass = ScoreComponent.class)
    @CollectionTable(name = "score_inf", joinColumns = @JoinColumn(name = "p_id", nullable = false))
    @MapKeyClass(String.class)
    @MapKeyColumn(name = "subject_name")
    private Map<String, ScoreComponent> scores = new HashMap<>();

    @ElementCollection(targetClass = NameComponent.class)
    @CollectionTable(name = "nick_inf", joinColumns = @JoinColumn(name = "p_id", nullable = false))
    @OrderColumn(name = "list_order")
    private List<NameComponent> nicks = new ArrayList<>();

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

    public Map<String, ScoreComponent> getScores() {
        return scores;
    }

    public void setScores(Map<String, ScoreComponent> scores) {
        this.scores = scores;
    }

    public List<NameComponent> getNicks() {
        return nicks;
    }

    public void setNicks(List<NameComponent> nicks) {
        this.nicks = nicks;
    }
}
