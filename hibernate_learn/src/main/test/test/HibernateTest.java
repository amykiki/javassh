package test;

import ch05.enumerated.Person;
import ch05.list.Person1;
import ch05.map.Person2;
import enums.Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import uitl.HibernateUtil;

import java.util.Arrays;

/**
 * Created by Amysue on 2016/4/18.
 */
public class HibernateTest {

    @Test
    public void testEnum() {
        Session     session = null;
        Transaction tx      = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Person p = new Person();
            p.setName("amy");
            p.setRole(Role.ADMIN);
            /*p.setName("kevin");
            p.setRole(Role.ALL);*/
            session.save(p);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void testList() {
        Session     session;
        Transaction tx      = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            /*Person1 p = new Person1();
            p.setName("amy");
            p.setAge(29);
            p.getSchools().add("小学");
            p.getSchools().add("中学");*/

            Person1 p = (Person1)session.load(Person1.class, 1);
            System.out.println(p.getName() + "->" + p.getAge());
            System.out.println(p.getSchools().size());
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void testMap() {
        Session     session;
        Transaction tx      = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Person2 p = new Person2();
            p.setName("kevin");
            p.setAge(31);
            p.getScores().put("计算机", new Float(100));
            p.getScores().put("数学", new Float(85.5));

            /*Person1 p = (Person1)session.load(Person1.class, 1);
            System.out.println(p.getName() + "->" + p.getAge());
            System.out.println(p.getSchools().size());*/
            session.save(p);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }
    }

}