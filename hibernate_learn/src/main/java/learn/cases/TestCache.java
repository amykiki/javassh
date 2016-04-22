package learn.cases;

import learn.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;
import uitl.HibernateUtil;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Amysue on 2016/4/22.
 */
public class TestCache {
    Session session;

    @Test
    public void test01() {
        try {
            session = HibernateUtil.getSession();
            List<Student> stus = session.createQuery("select stu from Student stu")
                    .setFirstResult(0)
                    .setMaxResults(50)
                    .list();
            Iterator<Student> iter = stus.iterator();
            while (iter.hasNext()) {
                Student s = iter.next();
                System.out.println(s.getName());
            }
            /**
             * 因为一级缓存的原因，所以此时去id为1的student不用重新发sql
             */
            Student stu = (Student) session.load(Student.class, 1);
            System.out.println("-----" + stu.getName() + "-----");

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }

        try {
            session = HibernateUtil.getSession();

            /**
             * 因为一级缓存随着session关闭就清空，所以此时去id为1的student需要重新发送sql
             * 如果开启类二级缓存，那么此时获取student不需要重新发送sql
             */
            Student stu = (Student) session.load(Student.class, 1);
            System.out.println("-----" + stu.getName() + "-----");

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
            Iterator<Student> stus = session.createQuery("from Student")
                    .setFirstResult(0).setMaxResults(50)
                    .iterate();
            while (stus.hasNext()) {
                Student stu = stus.next();
                System.out.println(stu.getId() + "," + stu.getName());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test03() {
        try {
            session = HibernateUtil.getSession();
            List<Student> stus = session.createQuery("select stu from Student stu")
                    .setFirstResult(1281)
                    .setMaxResults(50)
                    .list();
            Iterator<Student> iter = stus.iterator();
            while (iter.hasNext()) {
                Student s = iter.next();
                System.out.println(s.getName());
            }
            Iterator<Student> stusi = session.createQuery("from Student")
                    .setFirstResult(0).setMaxResults(50).iterate();
            while (stusi.hasNext()) {
                Student stu = stusi.next();
                System.out.println(stu.getId() + "," + stu.getName());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }

    }

    @Test
    public void test04() {
        try {
            session = HibernateUtil.getSession();
            List<Student> stus = session.createQuery("select stu from Student stu")
                    .setFirstResult(0)
                    .setMaxResults(50)
                    .list();
            Iterator<Student> iter = stus.iterator();
            while (iter.hasNext()) {
                Student s = iter.next();
                System.out.println(s.getName());
            }

            stus = session.createQuery("select stu from Student stu")
                    .setFirstResult(0)
                    .setMaxResults(50)
                    .list();
            iter = stus.iterator();
            while (iter.hasNext()) {
                Student s = iter.next();
                System.out.println(s.getName());
            }


        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }

    }

    @Test
    public void test05() {
        try {
            session = HibernateUtil.getSession();
            List<Student> stus = session.createQuery("select stu from Student stu")
                    .setCacheable(true)
                    .setFirstResult(0)
                    .setMaxResults(50)
                    .list();
            Iterator<Student> iter = stus.iterator();
            while (iter.hasNext()) {
                Student s = iter.next();
                System.out.println(s.getName());
            }

            stus = session.createQuery("select stu from Student stu")
                    .setCacheable(true)
                    .setFirstResult(0)
                    .setMaxResults(50)
                    .list();
            iter = stus.iterator();
            while (iter.hasNext()) {
                Student s = iter.next();
                System.out.println(s.getName());
            }
            System.out.println("Pass");

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }

    }
}
