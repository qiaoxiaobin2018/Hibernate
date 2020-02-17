package cn.itcast.manyToMany.hibernate.demo;

import cn.itcast.manyToMany.hibernate.utils.HibernateUtils;
import cn.itcast.oneToMany.entity.Customer;
import cn.itcast.manyToMany.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.*;
import org.junit.Test;

import java.util.List;

public class QueryObj {

    /*
    * 使用  Query 对象查询所有
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
            org.hibernate.Query from_user = session.createQuery("from User");
            List<User> list = from_user.list();
            System.out.println(list);

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
    *  使用 Query 对象条件查询
    * */
    @Test
    public void queryWithCondition(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            org.hibernate.Query query = session.createQuery("from User WHERE username=?");
            /*
            *  为参数赋值
            * 第一个参数：参数的索引位置，从 0 开始
            * 第二个参数：该位置参数的值
            * */
            query.setParameter(0, "Qiaozhi");

            List<User> list = query.list();
            System.out.println(list);

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
     *  使用 Query 对象查询  并 排序
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
            org.hibernate.Query query = session.createQuery("from User order by uid DESC");

            List<User> list = query.list();
            System.out.println(list);

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
     *  使用 Query 对象进行 分页
     * */
    @Test
    public void queryPagination(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            org.hibernate.Query query = session.createQuery("from User order by uid");

            //设置分页数据，开始位置 和 每页显示的记录数
            query.setFirstResult(0);
            query.setMaxResults(3);

            List<User> list = query.list();
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
     *  使用 Query 对象进行 投影查询
     * */
    @Test
    public void queryProjection(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            /*
            * 投影的是 属性名 而不是 列名
            * 查询得是 实体类对象 而不是 数据库表名
            * */
            org.hibernate.Query query = session.createQuery("SELECT username FROM User");

            List<Object> list = query.list();
            for(Object u:list){
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
     *  使用 Query 对象 count(*)
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
            /*
             * 投影的是 属性名 而不是 列名
             * 查询得是 实体类对象 而不是 数据库表名
             * */
            org.hibernate.Query query = session.createQuery("SELECT count(*) FROM User");

            // 1. 直接输出
            Object o = query.uniqueResult();
            System.out.println(o);

            // 2. 首先把 Object 类型变为 long 类型，在把 long 类型转化为 int 类型
            Long longValue = (Long)o;
            int value = longValue.intValue();
            System.out.println(value);

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
     *  使用 Query 对象 进行内连接
     * */
    @Test
    public void queryInnerJoin(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            /**
             *
             * */
            org.hibernate.Query query = session.createQuery("FROM Customer c inner join c.linkManSet");

            List<Object[]> list = query.list();
//            System.out.println(list);

            for(Object[] o:list){
//                System.out.println(o[0]);
                for(Object ele:o){
                    System.out.println(ele);
                }
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
     *  使用 Query 对象 进行迫切内连接
     * */
    @Test
    public void queryInnerJoinFetch(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            /**
             *
             * */
            org.hibernate.Query query = session.createQuery("FROM Customer c inner join fetch c.linkManSet");

            List<Customer> list = query.list();
//            System.out.println(list);

            for(Customer c:list){
                System.out.println(c);
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
     *  使用 Query 对象 进行左外连接
     * */
    @Test
    public void queryLeftOuterJoin(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            /**
             *
             * */
            org.hibernate.Query query = session.createQuery("FROM Customer c left outer join c.linkManSet");

            List<Object[]> list = query.list();
//            System.out.println(list);

            for(Object[] o:list){
                for(Object ele:o){
                    System.out.println(ele);
                }
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
     *  使用 Query 对象 进行迫切左外连接
     * */
    @Test
    public void queryLeftOuterJoinFetch(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            /**
             *
             * */
            org.hibernate.Query query = session.createQuery("FROM Customer c left outer join fetch c.linkManSet");

            List<Customer> list = query.list();
//            System.out.println(list);

            for(Customer c:list){
                System.out.println(c);
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






























