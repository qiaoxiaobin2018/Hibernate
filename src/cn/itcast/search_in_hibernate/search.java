package cn.itcast.search_in_hibernate;

import cn.itcast.oneToMany.entity.Customer;
import cn.itcast.oneToMany.entity.LinkMan;
import cn.itcast.oneToMany.hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class search {

    @Test
    public void get(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            /*
            * get 方法一经调用，马上发送 sql 语句查询数据库
            * */
            Customer customer = session.get(Customer.class,1);
            System.out.println(customer);

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

    @Test
    public void load(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            /*
             * 调用 load 方法之后，不会马上发送 sql 语句查询数据库
             *      此时 customer 中只有 cid
             * 只有获取对象的其他属性值时，才会查询数据库
             * */
            Customer customer = session.load(Customer.class,1);
            Thread.sleep(5000);
            System.out.println(customer.getCid());
            Thread.sleep(5000);
            System.out.println(customer);

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
    * 关联级别的延迟
    * */
    @Test
    public void fun(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            /*
             *
             * */
            Customer customer = session.load(Customer.class,1);
//            Thread.sleep(5000);
            Set<LinkMan> linkManSet = customer.getLinkManSet();
            System.out.println("customer.getLinkManSet()...");
            Thread.sleep(5000);
            System.out.println(linkManSet);

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
     * 批量抓取，减少数据库查询次数
     * */
    @Test
    public void batch(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            /*
             *  <set name="linkManSet" cascade="save-update,delete" batch-size="100">
             *  batch-size 值越大，查询数据库的次数越少
             * */
            List<Customer> customerList = session.createCriteria(Customer.class).list();
            for(Customer customer:customerList){
                System.out.println("\n\n"+customer.getCid());
                Set<LinkMan> linkManSet = customer.getLinkManSet();
                for(LinkMan linkman:linkManSet){
                    System.out.println(linkman.getLid() + " ## " +linkman.getLname());
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
}
