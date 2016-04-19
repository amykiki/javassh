package ch05.list;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amysue on 2016/4/18.
 */
@Entity
@Table(name = "t_person_list")
public class Person1 {

    @Id @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int age;
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "t_school", joinColumns = {@JoinColumn(name = "p_id", nullable = false)})
//    @LazyCollection(LazyCollectionOption.EXTRA)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderColumn(name = "list_order")
    @Column(name = "school_name")
    private List<String> schools = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getSchools() {
        return schools;
    }

    public void setSchools(List<String> schools) {
        this.schools = schools;
    }
}
