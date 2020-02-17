package cn.itcast.manyToMany.hibernate.demo;

import cn.itcast.manyToMany.entity.User;
import cn.itcast.manyToMany.hibernate.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.List;

public class CriteriaQuery {

    /*
     * 使用  Criteria 对象查询所有
     * */
    @Test
    public void queryAll(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            //创建对象
            Criteria criteria = session.createCriteria(User.class);
            //调用方法得到结果
            List<User> list = criteria.list();
            for(User u:list){
                System.out.println(u);
            }

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            System.out.println("数据库操作出错！");
            System.out.println("异常信息：");
            System.out.println(e.getMessage());
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    /*
     * 使用  Criteria 对象查询所有
     * */
    @Test
    public void queryWithConditions(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            //创建对象
            Criteria criteria = session.createCriteria(User.class);
            /*
            *  使用 Criteria 对象中的方法设置条件值
            * 1. add()，表示添加条件值
            * 2. 在 add() 方法中使用 对象中的方法 实现条件值的设置
            * */
            criteria.add(Restrictions.eq("username","Qiaozhi"));
            List<User> list = criteria.list();
            for(User u:list){
                System.out.println(u);
            }

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            System.out.println("数据库操作出错！");
            System.out.println("异常信息：");
            System.out.println(e.getMessage());
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    /*
     * 使用  Criteria 对象查询 并 排序
     * */
    @Test
    public void queryWithOrder(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            //创建对象
            Criteria criteria = session.createCriteria(User.class);

            criteria.addOrder(Order.desc("uid"));

            List<User> list = criteria.list();
            for(User u:list){
                System.out.println(u);
            }

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            System.out.println("数据库操作出错！");
            System.out.println("异常信息：");
            System.out.println(e.getMessage());
        }finally {
            session.close();
            sessionFactory.close();
        }
    }


    /*
     * 使用  Criteria 对象查询 并  分页
     * */
    @Test
    public void queryPage(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            //创建对象
            Criteria criteria = session.createCriteria(User.class);

            //设置开始位置 和 每页的记录数
            criteria.setFirstResult(0);
            criteria.setMaxResults(2);

            criteria.addOrder(Order.asc("uid"));

            List<User> list = criteria.list();
            for(User u:list){
                System.out.println(u);
            }

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            System.out.println("数据库操作出错！");
            System.out.println("异常信息：");
            System.out.println(e.getMessage());
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    /*
     * 使用  Criteria 对象查询 并 统计结果条数
     * */
    @Test
    public void queryCount(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            //创建对象
            Criteria criteria = session.createCriteria(User.class);

            criteria.setProjection(Projections.rowCount());

            Object o = criteria.uniqueResult();
            System.out.println(o);

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            System.out.println("数据库操作出错！");
            System.out.println("异常信息：");
            System.out.println(e.getMessage());
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    /*
     * 使用 离线  Criteria 对象查询
     * */
    @Test
    public void queryDetached(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            //创建离线  criteria 对象
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
            Criteria criteria = detachedCriteria.getExecutableCriteria(session);

            List<User> list = criteria.list();
            for(User u:list){
                System.out.println(u);
            }

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            System.out.println("数据库操作出错！");
            System.out.println("异常信息：");
            System.out.println(e.getMessage());
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

}
