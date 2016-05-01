package learn.cases;

import learn.model.Classroom;
import learn.model.Special;
import learn.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import uitl.HibernateUtil;

import java.util.List;

/**
 * Created by Amysue on 2016/4/22.
 */
public class TestFetch {

    Session     session;
    Transaction tx;

    @Test
    public void test01() {
        try {
            session = HibernateUtil.getSession();
            Student stu = (Student) session.load(Student.class, 1);
            System.out.println(stu.getName() + "," + stu.getClassroom().getName() + "," + stu.getClassroom().getSpecial().getName());

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
            Student stu = (Student) session.load(Student.class, 1);
            System.out.println(stu.getName());

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
            /**
             *  在XML中配置类fetch=join 仅仅只是对load对象有用，对HQL中查询对象无用
             *  *  所以此时会发出查询班级SQL，解决这个SQL的问题有两种方案
             *  一种是设置对象的抓取batch-size
             *  另一种是在HQL中使用fetch俩指定提取
             */
            List<Student> stus = session.createQuery("select stu from Student stu").list();
            for (Student stu : stus) {
//                System.out.println(stu.getName() +"," + stu.getClassroom().getName());
                stu.getClassroom().getName();
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
            /**
             *  在XML中配置类fetch=join 仅仅只是对load对象有用，对HQL中查询对象无用
             *  *  所以此时会发出查询班级SQL，解决这个SQL的问题有两种方案
             *  一种是设置对象的抓取batch-size
             *  另一种是在HQL中使用fetch俩指定提取
             *  特别注意，如果使用类join fetch就无法使用count(*)
             */
            List<Student> stus = session.createQuery("select stu from Student stu join fetch stu.classroom").list();
            for (Student stu : stus) {
                System.out.println(stu.getName() +"," + stu.getClassroom().getName());
//                stu.getClassroom().getName();
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
            Classroom cla = (Classroom) session.load(Classroom.class, 1);
            for (Student stu : cla.getStus()) {
                System.out.println(stu.getName());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test06() {
        try {
            session = HibernateUtil.getSession();
            /**
             * 对于通过HQL取班级列表并获取相应的学生列表时， fetch=join就无效类
             * 第一种方案可以设置set的batch-size来完成批量的抓取
             * 第二种方案可以设置fetch=subselect来使用subselect来完成根据查询出来的班级进行一次学生对象的子查询
             */
            List<Classroom> clas = session.createQuery("select cla from Classroom cla").list();
            for (Classroom cla : clas) {
                System.out.println(cla.getName());
                for (Student stu : cla.getStus()) {
                    System.out.println(stu.getName() + "," + cla.getName() + "," + cla.getGrade());
//                    stu.getName();
                }
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }



}
