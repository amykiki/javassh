package learn.cases;
import learn.model.Classroom;
import learn.model.Special;
import learn.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import uitl.HibernateUtil;

import java.util.Random;

import static org.junit.Assert.*;
/**
 * Created by Amysue on 2016/4/21.
 */
public class TestAdd {
    Session session;
    Transaction tx;
    Random ran = new Random();

    @Test
    public void testAddSpecial() {
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(new Special("计算机教育", "教育类"));
            session.save(new Special("计算机应用技术","高职类"));
            session.save(new Special("计算机网络技术","高职类"));
            session.save(new Special("计算机信息管理","高职类"));
            session.save(new Special("数学教育","教育类"));
            session.save(new Special("物理教育","教育类"));
            session.save(new Special("化学教育","教育类"));
            session.save(new Special("会计","高职类"));
            session.save(new Special("英语教育","教育类"));
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void testAddClassRoom() {
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(new Classroom("计算机教育1班", 2009, new Special(1)));
            session.save(new Classroom("计算机教育2班",2009,new Special(1)));
            session.save(new Classroom("计算机教育班",2010,new Special(1)));
            session.save(new Classroom("计算机教育班",2011,new Special(1)));
            session.save(new Classroom("计算机应用技术",2009,new Special(2)));
            session.save(new Classroom("计算机应用技术",2010,new Special(2)));
            session.save(new Classroom("计算机应用技术",2011,new Special(2)));
            session.save(new Classroom("计算机网络技术",2009,new Special(3)));
            session.save(new Classroom("计算机网络技术",2010,new Special(3)));
            session.save(new Classroom("计算机网络技术",2011,new Special(3)));
            session.save(new Classroom("计算机信息管理",2009,new Special(4)));
            session.save(new Classroom("计算机信息管理",2010,new Special(4)));
            session.save(new Classroom("计算机信息管理",2011,new Special(4)));
            session.save(new Classroom("数学教育1班",2009,new Special(5)));
            session.save(new Classroom("数学教育2班",2009,new Special(5)));
            session.save(new Classroom("数学教育3班",2009,new Special(5)));
            session.save(new Classroom("数学教育1班",2010,new Special(5)));
            session.save(new Classroom("数学教育2班",2010,new Special(5)));
            session.save(new Classroom("数学教育1班",2011,new Special(5)));
            session.save(new Classroom("数学教育2班",2011,new Special(5)));
            session.save(new Classroom("物理教育",2009,new Special(6)));
            session.save(new Classroom("物理教育",2010,new Special(6)));
            session.save(new Classroom("物理教育",2011,new Special(6)));
            HibernateUtil.flushSession();
            session.save(new Classroom("化学教育",2009,new Special(7)));
            session.save(new Classroom("化学教育",2010,new Special(7)));
            session.save(new Classroom("化学教育",2011,new Special(7)));
            session.save(new Classroom("会计",2009,new Special(8)));
            session.save(new Classroom("会计",2010,new Special(8)));
            session.save(new Classroom("会计",2011,new Special(8)));
            session.save(new Classroom("英语教育A班",2009,new Special(9)));
            session.save(new Classroom("英语教育B班",2009,new Special(9)));
            session.save(new Classroom("英语教育A班",2010,new Special(9)));
            session.save(new Classroom("英语教育B班",2010,new Special(9)));
            session.save(new Classroom("英语教育A班",2011,new Special(9)));
            session.save(new Classroom("英语教育B班",2011,new Special(9)));
            session.save(new Classroom("选修课班A",2011,null));
            session.save(new Classroom("选修课班B",2011,null));
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void testAddStudent() {
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            for (int i = 1; i < 33; i++) {
                for (int j = 0; j < 40; j++) {
                    Student stu = new Student(getName(), getSex(), new Classroom(i));
                    session.save(stu);
                }
                if (i % 20 == 0) {
                    HibernateUtil.flushSession();
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void testAddStudentNULL() {
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            for (int j = 0; j < 100; j++) {
                Student stu = new Student(getName(), getSex(), null);
                session.save(stu);
            }
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    private String  getSex() {
        String[] sexes = new String[]{"男", "女"};
        return sexes[ran.nextInt(2)];
    }

    private String getName() {
        String[] name1 = new String[]{"孔","张","叶","李","叶入","孔令",
                                      "张立","陈","刘","牛","夏侯","令","令狐","赵","母","穆","倪",
                                      "张毅","称","程","王","王志","刘金","冬","吴","马","沈"};

        String[] name2 = new String[]{"凡","课","颖","页","源","都",
                                      "浩","皓","西","东","北","南","冲","昊","力","量","妮",
                                      "敏","捷","杰","坚","名","生","华","鸣","蓝","春","虎","刚","诚"};

        String[] name3 = new String[]{"吞","明","敦","刀","备","伟",
                                      "唯","楚","勇","诠","佺","河","正","震","点","贝","侠",
                                      "伟","大","凡","琴","青","林","星","集","财"};

        boolean two = ran.nextInt(50)>=45?false:true;
        if(two) {
            String n1 = name1[ran.nextInt(name1.length)];
            String n2;
            int n = ran.nextInt(10);
            if(n>5) {
                n2 = name2[ran.nextInt(name2.length)];
            } else {
                n2 = name3[ran.nextInt(name3.length)];
            }
            return n1+n2;
        } else {
            String n1 = name1[ran.nextInt(name1.length)];
            String n2 = name2[ran.nextInt(name2.length)];
            String n3 = name3[ran.nextInt(name3.length)];
            return n1+n2+n3;
        }
    }
}


