package learn.cases;

import learn.model.Student;
import org.hibernate.Cache;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;
import uitl.HibernateUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Amysue on 2016/4/22.
 */
public class TestSecondCache {
    Session session;
    @Test
    public void test01() {
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


        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }

        try {
            Map cacheEntries = HibernateUtil.getSf().getStatistics().getSecondLevelCacheStatistics("learn.model.Student").getEntries();
            System.out.println(cacheEntries);
            session = HibernateUtil.getSession();
            Iterator<Student> stusi = session.createQuery("from Student")
                    .setFirstResult(0).setMaxResults(50).iterate();
            while (stusi.hasNext()) {
                Student stu = stusi.next();
                System.out.println(stu.getId() + "," + stu.getName());
            }

            System.out.println("========================================");
            stat();
        }catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }

    }

    @Test
    public void test02() {
        try {
            session = HibernateUtil.getSession();
            List<Object[]> stus = session.createQuery("select stu.id, stu.name from Student stu")
                    .setFirstResult(0)
                    .setMaxResults(50)
                    .list();
            for (Object[] o : stus) {
                System.out.println(o[0] +"," + o[1]);
            }


        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }

        try {
            Map cacheEntries = HibernateUtil.getSf().getStatistics().getSecondLevelCacheStatistics("learn.model.Student").getEntries();
            System.out.println(cacheEntries);
            session = HibernateUtil.getSession();
            Student stu = (Student) session.load(Student.class, 1);
            System.out.println(stu.getId() + "," + stu.getName());


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
                    .setCacheable(true)
                    .setFirstResult(0)
                    .setMaxResults(50)
                    .list();
            Iterator<Student> iter = stus.iterator();
            while (iter.hasNext()) {
                Student s = iter.next();
                System.out.println(s.getName());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }

        try {
            Map cacheEntries = HibernateUtil.getSf().getStatistics().getSecondLevelCacheStatistics("learn.model.Student").getEntries();
            System.out.println(cacheEntries);
            session = HibernateUtil.getSession();
            Student firstStu = (Student) session.load(Student.class, 1);
            System.out.println(firstStu.getName());
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
            stat();
            queryStat();
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
            List<Student> stus = session.createQuery("select stu from Student stu where name like ?")
                    .setCacheable(true)
                    .setParameter(0, "%王%")
                    .setFirstResult(0)
                    .setMaxResults(50)
                    .list();
            Iterator<Student> iter = stus.iterator();
            while (iter.hasNext()) {
                Student s = iter.next();
                System.out.println(s.getName());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }

        try {
            Map cacheEntries = HibernateUtil.getSf().getStatistics().getSecondLevelCacheStatistics("learn.model.Student").getEntries();
            System.out.println(cacheEntries);
            session = HibernateUtil.getSession();
            List<Student> stus = session.createQuery("select stu from Student stu where name like ?")
                    .setCacheable(true)
                    .setParameter(0, "%张%")
                    .setFirstResult(0)
                    .setMaxResults(50)
                    .list();
            Iterator<Student> iter = stus.iterator();
            while (iter.hasNext()) {
                Student s = iter.next();
                System.out.println(s.getName());
            }
            stat();
            queryStat();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }

    }

    private void stat() {
        long hitCount = HibernateUtil.getSf().getStatistics().getSecondLevelCacheHitCount();
        System.out.println("hitCount = " + hitCount);
    }
    private void queryStat() {
        long hitCount = HibernateUtil.getSf().getStatistics().getQueryCacheHitCount();
        System.out.println("QueryCacheHitCount = " + hitCount);
    }
}
