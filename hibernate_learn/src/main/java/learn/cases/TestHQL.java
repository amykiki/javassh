package learn.cases;
import learn.model.Classroom;
import learn.model.Special;
import learn.model.Student;
import learn.model.StudentDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import uitl.HibernateUtil;

import java.util.List;

/**
 * Created by Amysue on 2016/4/21.
 */
public class TestHQL {
    Session session;
    Transaction tx;

    @Test
    public void test01() {
        try {
            session = HibernateUtil.getSession();
            List<Special> spes = session.createQuery("from learn.model.Special").list();
            for (Special spe : spes) {
                for (Classroom cla : spe.getClas()) {
                    System.out.println(spe.getName() + " " + cla.getName());
                }
            }


        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test02() {
//       use join fetch
        try {
            session = HibernateUtil.getSession();
            List<Special> spes = session.createQuery("from learn.model.Special spe join fetch spe.clas").list();
            for (Special spe : spes) {
                for (Classroom cla : spe.getClas()) {
                    System.out.println(spe.getName() + " " + cla.getName());
                }
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
            List<Special> spes = session.createQuery("select spe from learn.model.Special spe").list();
            for (Special spe : spes) {
                System.out.println(spe.getName());
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
            List<Student> stus = session.createQuery("select stu from Student stu where stu.name like ?")
                                 .setParameter(0, "%张%")
                                 .list();
            for (Student stu : stus) {
                System.out.println(stu.getId() + " " + stu.getName());
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
            List<Student> stus = session.createQuery("select stu from Student stu join fetch stu.classroom where stu.name like ?")
                    .setParameter(0, "%张%")
                    .list();
            for (Student stu : stus) {
                System.out.println(stu.getId() + " " + stu.getName());
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
            List<Student> stus = session.createQuery("select stu from Student stu where stu.name like :name")
                    .setParameter("name", "%李%")
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .list();
            for (Student stu : stus) {
                System.out.println(stu.getId() + " " + stu.getName());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test07() {
        try {
            session = HibernateUtil.getSession();
            List<Student> stus = session.createQuery("select stu from Student stu where stu.name like ?1")
                    .setParameter("1", "%王%")
                    .list();
            for (Student stu : stus) {
                System.out.println(stu.getId() + " " + stu.getName());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test08() {
        try {
            session = HibernateUtil.getSession();
            long num = (long)session.createQuery("select count(*) from Student stu where name like :name and sex = :sex")
                    .setParameter("name", "%刘%")
                    .setParameter("sex", "女")
                    .uniqueResult();
            System.out.println("num = " + num);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test09() {
        try {
            session = HibernateUtil.getSession();
            Student stu = (Student)session.createQuery("select stu from Student stu where id = :id")
                    .setParameter("id", 16)
                    .uniqueResult();
            System.out.println(stu.getName());
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test10() {
        try {
            session = HibernateUtil.getSession();
            List<Object[]> datas = session.createQuery("select stu.sex, count(*) from Student stu group by stu.sex")
                                   .list();
            for (Object[] data : datas) {
                System.out.println(data[0] + ":" + data[1]);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test11() {
        try {
            session = HibernateUtil.getSession();
            List<Student> stus = session.createQuery("select stu from Student stu where stu.classroom.name = ?1 and stu.name like ?2")
                    .setParameter("1", "计算机教育班")
                    .setParameter("2", "%张%")
                    .list();
            for (Student stu : stus) {
                System.out.println(stu.getName());
            }
            System.out.println(stus.size());
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test12() {
        try {
            session = HibernateUtil.getSession();
            List<Student> stus = session.createQuery("select stu from Student stu where stu.name like ? and stu.classroom.id in (:clas)")
                    .setParameter(0, "%张%")
                    .setParameterList("clas", new Integer[]{1, 2})
                    .list();
            for (Student stu : stus) {
                System.out.println(stu.getName());
            }
            System.out.println(stus.size());
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test13() {
        try {
            session = HibernateUtil.getSession();
            List<Student> stus = session.createQuery("select stu from Student stu where stu.classroom.id in (:clas)")
                    .setParameterList("clas", new Integer[]{1, 2})
                    .setFirstResult(0)
                    .setMaxResults(15)
                    .list();
            for (Student stu : stus) {
                System.out.println(stu.getName());
            }
            System.out.println(stus.size());
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test14() {
        try {
            session = HibernateUtil.getSession();
            List<Student> stus = session.createQuery("select stu from Student stu where stu.classroom is null")
                    .list();
            for (Student stu : stus) {
                System.out.println(stu.getName());
            }
            System.out.println(stus.size());
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test15() {
        try {
            session = HibernateUtil.getSession();
            List<Object[]> stus = session.createQuery("select clas.name, count (stu.name) from Student stu right join stu.classroom clas group by clas.name")
                                  .list();
            for (Object[] status : stus) {
                System.out.println(status[0] + " " + status[1]);
            }

            stus = session.createQuery("select clas.name, count (clas.name) from Classroom clas group by clas.name")
                    .list();
            for (Object[] status : stus) {
                System.out.println(status[0] + " " + status[1]);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test16() {
        try {
            session = HibernateUtil.getSession();
            List<StudentDao> stus = session.createQuery("select new learn.model.StudentDao(stu.id, stu.name, stu.sex, cla.name as cName, spe.name as sName)" +
                                                      " from Student stu left join stu.classroom cla left join cla.special spe")
                                  .list();
            for (StudentDao status : stus) {
                System.out.println(status);
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }



    @Test
    public void test17() {
        try {
            session = HibernateUtil.getSession();
            List<Object[]> stus = session.createQuery("select spe.name, count (stu.name) as num from Student stu " +
                                                              "right join stu.classroom.special spe group by spe.name " +
                                                              "having count (stu.name) > 120")
                    .list();
            for (Object[] status : stus) {
                System.out.println(status[0] + " " + status[1]);
            }

            stus = session.createQuery("select spe.name, count (clas.name) from Classroom clas right join clas.special spe group by spe.name")
                    .list();
            for (Object[] status : stus) {
                System.out.println(status[0] + " " + status[1]);
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void test18() {
        try {
            session = HibernateUtil.getSession();
            List<Object[]> stus = session.createQuery("select distinct spe.name, stu.sex, count (stu.name) as num from Student stu " +
                                                              "right join stu.classroom.special spe group by spe.name, stu.sex")
                                                        .list();
            for (Object[] status : stus) {
                System.out.println(status[0] + " " + status[1] + " " + status[2]);
            }

            stus = session.createQuery("select spe.name, count (clas.name) from Classroom clas right join clas.special spe group by spe.name")
                    .list();
            for (Object[] status : stus) {
                System.out.println(status[0] + " " + status[1]);
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }






}
