package doc.entity;

import javax.persistence.*;

/**
 * Created by Amysue on 2016/5/2.
 */
@Entity
@Table(name = "t_dep")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(nullable = false)
    private String name;

    public Department() {
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
