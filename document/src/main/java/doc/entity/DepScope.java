package doc.entity;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;

/**
 * Created by Amysue on 2016/5/5.
 */
@Entity
@Table(name = "t_dep_scope")
public class DepScope {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int depId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scope_id")
    private Department scopeDep;

    public DepScope() {
    }

    public DepScope(int depId, Department scopeDep) {
        this.depId = depId;
        this.scopeDep = scopeDep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public Department getScopeDep() {
        return scopeDep;
    }

    public void setScopeDep(Department scopeDep) {
        this.scopeDep = scopeDep;
    }

    @Override
    public String toString() {
        return "DepScope{" +
                "id=" + id +
                ", depId=" + depId +
                ", scopeDep=" + scopeDep +
                '}';
    }
}
