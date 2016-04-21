package uitl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by Amysue on 2016/4/18.
 */
public class HibernateUtil {
    private static final ThreadLocal<Session> SESSION_THREAD_LOCAL = new ThreadLocal<>();
    private static SessionFactory sf = null;

    private static SessionFactory getSf() {
        if (sf == null) {
            synchronized (HibernateUtil.class) {
                if (sf == null) {
                    synchronized (HibernateUtil.class) {
                        Configuration conf = new Configuration().configure();
                        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
                        sf = conf.buildSessionFactory(ssrb.build());
                    }
                }
            }
        }
        return sf;
    }
    public static Session getSession() {
        Session session = SESSION_THREAD_LOCAL.get();
        if (session == null) {
            session = getSf().openSession();
            SESSION_THREAD_LOCAL.set(session);
        }
        return session;
    }

    public static void closeSession() {
        Session session = SESSION_THREAD_LOCAL.get();
        SESSION_THREAD_LOCAL.set(null);
        if (session != null) {
            System.out.println("==========CLOSE SESSION=================");
            session.close();
        }
    }

    public static void flushSession() {
        Session session = SESSION_THREAD_LOCAL.get();
        if (session != null) {
            session.flush();
            session.clear();
        }
    }
}
