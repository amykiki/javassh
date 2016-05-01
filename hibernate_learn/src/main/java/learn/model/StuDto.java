package learn.model;

/**
 * Created by Amysue on 2016/4/22.
 */
public class StuDto {
    private Student stu;
    private Classroom cla;
    private Special spe;

    public StuDto() {
    }

    public StuDto(Student stu, Classroom cla, Special spe) {
        this.stu = stu;
        this.cla = cla;
        this.spe = spe;
    }

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    public Classroom getCla() {
        return cla;
    }

    public void setCla(Classroom cla) {
        this.cla = cla;
    }

    public Special getSpe() {
        return spe;
    }

    public void setSpe(Special spe) {
        this.spe = spe;
    }
}
