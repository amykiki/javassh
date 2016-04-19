package ch05.map;

import model.IBaseModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CollectionType;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amysue on 2016/4/18.
 */
@Entity
@Table(name = "t_person_map")
public class Person2 implements IBaseModel{
    @Id @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;

    @ElementCollection(targetClass = Float.class)
    @CollectionTable(name = "t_map", joinColumns = {@JoinColumn(name = "p_id", nullable = false)})
    @MapKeyColumn(name = "subject_name")
    @MapKeyClass(String.class)
    @Column(name = "mark")
    private Map<String, Float> scores;

    public Person2() {
        scores = new HashMap<>();
    }

    @Override
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Float> getScores() {
        return scores;
    }

    public void setScores(Map<String, Float> scores) {
        this.scores = scores;
    }
}
