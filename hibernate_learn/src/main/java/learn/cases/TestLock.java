package learn.cases;

import learn.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import uitl.HibernateUtil;

/**
 * Created by Amysue on 2016/4/22.
 */
public class TestLock {
    Session session;
    Transaction tx;

    @Test
    public void test01() {
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Student stu01 = (Student) session.load(Student.class, 1);
//            Student stu01 = (Student) session.load(Student.class, 1, LockOptions.UPGRADE);
            System.out.println(stu01.getId() + "," + stu01.getName() + "," + stu01.getSex());
            stu01.setName("JAY");
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            HibernateUtil.closeSession();
        }
    }
    @Test
    public void test02() {
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Student stu02 = (Student) session.load(Student.class, 1);
//            Student stu02 = (Student) session.load(Student.class, 1, LockOptions.UPGRADE);
            System.out.println(stu02.getId() + "," + stu02.getName() + "," + stu02.getSex());
            stu02.setSex("男");
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            HibernateUtil.closeSession();
        }
    }
}
