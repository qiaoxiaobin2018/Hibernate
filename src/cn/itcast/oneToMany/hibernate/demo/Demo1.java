package cn.itcast.oneToMany.hibernate.demo;

import cn.itcast.oneToMany.entity.Customer;
import cn.itcast.oneToMany.entity.LinkMan;
import cn.itcast.oneToMany.hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class Demo1 {

    /*
     * customer -- add
     * */
    @Test
    public void customerAdd(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            //正常操作
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            //开启事务
            transaction = session.beginTransaction();

            //CRUD 操作
            Customer customer = new Customer();
            customer.setCname("Google");
            customer.setClevel("A");
            customer.setCmobile("15536853551");
            customer.setCphone("15536853551");
            customer.setCsource("Internet");
            session.save(customer);

            //提交事务
            transaction.commit();

        }catch (Exception e){
            //回滚
            transaction.rollback();
            System.out.println("数据库操作出错，已回滚！");
            System.out.println("异常信息：");
            System.out.println(e.getMessage());
        }finally{
            //关闭资源连接
            /*与本地线程绑定的  session 与本地线程同生共死，不需要手动关闭*/
            session.close();
            sessionFactory.close();

        }
    }

    /*
     * linkmam -- add （重要）
     * */
    @Test
    public void linkmanAdd(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            //正常操作
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            //开启事务
            transaction = session.beginTransaction();

            //CRUD 操作
            Customer customer = session.get(Customer.class,1);

            LinkMan linkMan = new LinkMan();
            linkMan.setLname("张三");
            linkMan.setLgender("男");
            linkMan.setLphone("15536853889");

            //为客户添加联系人
            customer.getLinkManSet().add(linkMan);
            //为联系人设置客户
            /*
            * 需要配置
            * <set name="linkManSet" cascade="save-update">
            * */
//            linkMan.setCustomer(customer);

            //保存
            session.saveOrUpdate(customer);

            //提交事务
            transaction.commit();

        }catch (Exception e){
            //回滚
            transaction.rollback();
            System.out.println("数据库操作出错，已回滚！");
            System.out.println("异常信息：");
            System.out.println(e.getMessage());
        }finally{
            //关闭资源连接
            /*与本地线程绑定的  session 与本地线程同生共死，不需要手动关闭*/
            session.close();
            sessionFactory.close();

        }
    }

    /*
     * customer -- 级联删除
     * */
    @Test
    public void customerDelete(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            //正常操作
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            //开启事务
            transaction = session.beginTransaction();

            //CRUD 操作
            Customer customer = session.get(Customer.class,2);
            session.delete(customer);

            //提交事务
            transaction.commit();

        }catch (Exception e){
            //回滚
            transaction.rollback();
            System.out.println("数据库操作出错，已回滚！");
            System.out.println("异常信息：");
            System.out.println(e.getMessage());
        }finally{
            //关闭资源连接
            /*与本地线程绑定的  session 与本地线程同生共死，不需要手动关闭*/
            session.close();
            sessionFactory.close();

        }
    }

    /*
     * 一对多 修改
     * */
    @Test
    public void update(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            //正常操作
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            //开启事务
            transaction = session.beginTransaction();

            //CRUD 操作
            /*
            * 更改联系人所属的客户
            *   查询出目标客户的实体类和目标联系人的实体类，执行添加操作
            * */
            Customer customer = session.get(Customer.class,1);
            LinkMan linkMan = session.get(LinkMan.class,1);

            //直接将 linkman 添加到 customer 中
            customer.getLinkManSet().add(linkMan);

            //提交事务
            transaction.commit();

        }catch (Exception e){
            //回滚
            transaction.rollback();
            System.out.println("数据库操作出错，已回滚！");
            System.out.println("异常信息：");
            System.out.println(e.getMessage());
        }finally{
            //关闭资源连接
            /*与本地线程绑定的  session 与本地线程同生共死，不需要手动关闭*/
            session.close();
            sessionFactory.close();

        }
    }

}
