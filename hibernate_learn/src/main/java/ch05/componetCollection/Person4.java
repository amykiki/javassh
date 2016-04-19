package ch05.componetCollection;

import ch05.embeddable.NameEmbed;
import model.IBaseModel;

import javax.persistence.*;

/**
 * Created by Amysue on 2016/4/19.
 */
@Entity
@Table(name = "t_person_componet")
public class Person4 implements IBaseModel{

    @Id
    @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int age;
    private NameCollection name;

    @Override
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

    public NameCollection getName() {
        return name;
    }

    public void setName(NameCollection name) {
        this.name = name;
    }
}
