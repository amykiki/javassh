package learn.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Amysue on 2016/4/21.
 */
@Entity
@Table(name = "t_classroom")
//@BatchSize(size = 16)
public class Classroom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private int id;
    private String name;
    private int grade;
    @ManyToOne(targetEntity = Special.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "s_id", referencedColumnName = "s_id")
    private Special special;
    @OneToMany(targetEntity = Student.class, mappedBy = "classroom")
//    @Fetch(FetchMode.SUBSELECT)
//    @BatchSize(size = 10)
    private Set<Student> stus;

    public Classroom() {
    }

    public Classroom(String name, int grade, Special special) {
        this.name = name;
        this.grade = grade;
        this.special = special;
    }

    public Classroom(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Special getSpecial() {
        return special;
    }

    public void setSpecial(Special special) {
        this.special = special;
    }

    public Set<Student> getStus() {
        return stus;
    }

    public void setStus(Set<Student> stus) {
        this.stus = stus;
    }
}
