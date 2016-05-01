package learn.cases;

import learn.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.junit.Test;
import uitl.HibernateUtil;

import java.util.List;

/**
 * Created by Amysue on 2016/4/22.
 */
public class TestSQL {
    Session session;

    @Test
    public void test01() {
        try {
            session = HibernateUtil.getSession();
            List<Object[]> stus = session.createSQLQuery("SELECT {t1.*}, {t2.*}, {t3.*} FROM t_stu t1 " +
                                                                 "LEFT JOIN t_classroom t2 " +
                                                                 "on t1.c_id = t2.c_id " +
                                                                 "LEFT JOIN t_spe t3 " +
                                                                 "on t2.s_id = t3.s_id WHERE t1.name LIKE ?")
                                 .addEntity("t1", Student.class)
                                 .addEntity("t2", Classroom.class)
                                 .addEntity("t3", Special.class)
                                 .setParameter(0, "%王%")
                                 .setFirstResult(0)
                                 .setMaxResults(20)
                                 .list();
            Student stu = null;
            Classroom cla = null;
            Special spe = null;
            for (Object[] info: stus) {
                stu = (Student) info[0];
                System.out.println(stu.getName() + "," + stu.getClassroom().getName() + "," + stu.getClassroom().getSpecial().getName());
                cla = (Classroom) info[1];
                spe = (Special) info[2];
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test02() {
        try {
            session = HibernateUtil.getSession();
            List<StudentDao> stus = session.createSQLQuery("SELECT t1.p_id AS id, t1.name as name," +
                                                                 "t1.sex AS sex, t2.name as cName, t3.name as sName " +
                                                                 "FROM t_stu t1 " +
                                                                 "LEFT JOIN t_classroom t2 " +
                                                                 "on t1.c_id = t2.c_id " +
                                                                 "LEFT JOIN t_spe t3 " +
                                                                 "on t2.s_id = t3.s_id WHERE t1.name LIKE ?")
                    .setResultTransformer(Transformers.aliasToBean(StudentDao.class))
                    .setParameter(0, "%王%")
                    .setFirstResult(0)
                    .setMaxResults(20)
                    .list();
            for (StudentDao s : stus) {
                System.out.println(s);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
