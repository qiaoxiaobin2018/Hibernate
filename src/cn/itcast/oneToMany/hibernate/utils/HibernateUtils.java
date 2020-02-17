package cn.itcast.oneToMany.hibernate.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static Configuration cfg = null;
    private static SessionFactory sessionFactory = null;

    /*
    * 让一个项目只有一个 sessionFactory ,而不用每一次都创建 sessionFactory (创建时要更新表，资源消耗大)
    * 使用静态代码块实现
    * */
    static {
        cfg = new Configuration();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    public static Configuration getCfg() {
        return cfg;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSessionObject(){
        return sessionFactory.getCurrentSession();
    }

}
