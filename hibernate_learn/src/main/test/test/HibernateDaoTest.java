package test;

import ch05.collectionComponent.NameComponent;
import ch05.collectionComponent.Person5;
import ch05.collectionComponent.ScoreComponent;
import ch05.componetCollection.NameCollection;
import ch05.componetCollection.Person4;
import ch05.embeddable.NameEmbed;
import ch05.embeddable.Person3;
import ch05.enumerated.Person;
import ch05.map.Person2;
import ch06.many2many.Address10;
import ch06.many2many.Person10;
import ch06.many2one.Address6;
import ch06.many2one.Address9;
import ch06.many2one.Person6;
import ch06.many2one.Person9;
import ch06.one2many.Address8;
import ch06.one2many.Person8;
import ch06.one2one.Address7;
import ch06.one2one.Person7;
import dao.HibernateDao;
import model.IBaseModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.dialect.lock.PessimisticWriteSelectLockingStrategy;
import org.junit.Test;
import uitl.HibernateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Amysue on 2016/4/19.
 */
public class HibernateDaoTest {
    HibernateDao<IBaseModel> hDao = new HibernateDao<>();

    @Test
    public void testAdd() throws Exception {
        Person2 p = new Person2();
        p.setAge(25);
        p.setName("Jay");
        p.getScores().put("大提琴", new Float(100));
        p.getScores().put("数学", new Float(75));
        hDao.add(p);
        System.out.println(p.getId());
    }

    @Test
    public void testAdd3() throws Exception {
        Person3 p = new Person3();
        p.setAge(28);
        NameEmbed n = new NameEmbed();
        n.setFirst("Amy");
        n.setLast("Zou");
        p.setName(n);
        hDao.add(p);
        System.out.println(p.getId());
        System.out.println(p.getId());
    }

    @Test
    public void testAdd4() throws Exception {
        Person4 p = new Person4();
        p.setAge(28);
        NameCollection n = new NameCollection();
        n.setFirst("Amy");
        n.setLast("Zou");
        Map<String, Integer> power = new HashMap<>();
        power.put("优秀", 90);
        n.setPower(power);
        p.setName(n);
        hDao.add(p);
        System.out.println(p.getId());
        System.out.println(p.getId());
    }

    @Test
    public void testAdd5() throws Exception {
        Person5 p = new Person5();
        p.setAge(30);
        p.getNicks().add(new NameComponent("amy", "zou"));
        p.getScores().put("数学", new ScoreComponent("优秀", 95));
        hDao.add(p);
        System.out.println(p.getId());
    }

    @Test
    public void testAdd6() throws Exception {
        Session session;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Person6 p = new Person6();
            p.setAge(30);
            p.setName("kevin");
            Address6 a = new Address6();
            a.setAddressInfo("上海世博园");
//            Address6 a = (Address6) session.load(Address6.class, 2);
            p.setAddress(a);
            session.persist(p);
          /*  Person6 p = (Person6) session.load(Person6.class, 3);
            session.delete(p);*/
//            System.out.println(p.getAddress().getAddressInfo());

            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }

    }

    @Test
    public void testAdd7() throws Exception {
        Session session;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Person7 p = new Person7();
            p.setAge(29);
            p.setName("amy");
            Address7 a = new Address7();
            a.setAddressInfo("上海世博园六街坊");
//            Address7 a = (Address7) session.load(Address7.class, 1);
            p.setAddress(a);
            session.save(p);
          /*  Person6 p = (Person6) session.load(Person6.class, 3);
            session.delete(p);*/
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }

    }

    @Test
    public void testAdd8() throws Exception {
        Session session;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            /*Person8 p = new Person8();
            p.setAge(29);
            p.setName("amy");
            Address8 a = new Address8();
            a.setAddressInfo("上海世博园");*/
//            p.getAddress().add(a);
//            session.save(p);
//            session.save(a);
            Person8 p = (Person8) session.load(Person8.class, 1);
            p.getAddress().add((Address8) session.load(Address8.class, 1));
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }

    }
    @Test
    public void testAdd9() throws Exception {
        Session session;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Person9 p = new Person9();
            p.setName("amy");
            p.setAge(29);
            session.save(p);
            Address9 a = new Address9();
            a.setAddressInfo("上海闵行浦江镇");
            a.setPerson(p);
            session.persist(a);
            Address9 a2 = new Address9();
            a2.setAddressInfo("上海虹桥机场");
            a2.setPerson(p);
            session.persist(a2);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }

    }

    @Test
    public void testAdd10() throws Exception {
        Session session;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            /*Person10 p = new Person10();
            p.setName("amy");
            p.setAge(29);
            session.save(p);
            Address10 a = new Address10();
            a.setAddressInfo("上海闵行浦江镇");
            a.getPersons().add(p);
            session.persist(a);*/
            Person10 p = (Person10) session.load(Person10.class, 2);
            System.out.println(p.getName());
            System.out.println(p.getAddress().toString());
            

            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }

    }


    @Test
    public void testLoad() throws Exception {
        Session session;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Person2 p = (Person2) session.load(Person2.class, 1);
           /* System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getAge());
            System.out.println(p.getScores().get("数学"));*/
            p.setName("小公主");
            session.update(p);
            session.delete(p);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void testLoad4() throws Exception {
        Session session;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Person4 p = (Person4) session.load(Person4.class, 1);
            p.getName().getPower().put("良好", 80);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }
    }


}