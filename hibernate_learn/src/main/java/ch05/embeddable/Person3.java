package ch05.embeddable;

import model.IBaseModel;

import javax.persistence.*;

/**
 * Created by Amysue on 2016/4/19.
 */
@Entity
@Table(name = "t_person_embed")
public class Person3 implements IBaseModel{

    @Id
    @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int age;
    private NameEmbed name;

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

    public NameEmbed getName() {
        return name;
    }

    public void setName(NameEmbed name) {
        this.name = name;
    }
}
