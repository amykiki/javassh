package learn.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Amysue on 2016/4/21.
 */
@Entity
@Table(name = "t_spe")
public class Special {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private int id;
    private String name;
    private String type;
//    @OneToMany(targetEntity = Classroom.class, mappedBy = "special", fetch = FetchType.EAGER)
    @OneToMany(targetEntity = Classroom.class, mappedBy = "special", fetch = FetchType.LAZY)
    private Set<Classroom> clas;

    public Special() {
    }

    public Special(int id) {
        this.id = id;
    }

    public Special(String name, String type) {
        this.name = name;
        this.type = type;
    }


    public Set<Classroom> getClas() {
        return clas;
    }

    public void setClas(Set<Classroom> clas) {
        this.clas = clas;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
