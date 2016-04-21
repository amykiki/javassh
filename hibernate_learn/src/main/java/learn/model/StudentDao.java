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
