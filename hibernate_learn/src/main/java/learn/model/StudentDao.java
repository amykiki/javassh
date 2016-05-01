package learn.model;

/**
 * Created by Amysue on 2016/4/21.
 */
public class StudentDao {
    private int id;
    private String name;
    private String sex;
    private String cName;
    private String sName;

    public StudentDao() {
    }

    public StudentDao(int id, String name, String sex, String cName, String sName) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.cName = cName;
        this.sName = sName;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    @Override
    public String toString() {
        return "StudentDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", cName='" + cName + '\'' +
                ", sName='" + sName + '\'' +
                '}';
    }
}
