package cn.itcast.manyToMany.hibernate.demo;

import cn.itcast.manyToMany.hibernate.utils.HibernateUtils;
import cn.itcast.manyToMany.entity.Role;
import cn.itcast.manyToMany.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class Demo1 {

    /*
    * 创建两个表
    * */
    @Test
    public void fun1(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */



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
     * 多对多级联保存
     * */
    @Test
    public void save(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            //创建两个 User 和 两个 role
            User u1 = new User();
            u1.setUsername(" zhangsan ");
            User u2 = new User();
            u2.setUsername(" wangwu ");

            Role r1 =  new Role();
            r1.setRname(" Leader ");
            Role r2 = new Role();
            r2.setRname(" Officer ");
            Role r3 = new Role();
            r3.setRname(" President ");

            //添加角色
            u1.getRoleSet().add(r1);
            u1.getRoleSet().add(r2);

            u2.getRoleSet().add(r2);
            u2.getRoleSet().add(r3);

            //保存用户即可
            session.save(u1);
            session.save(u2);

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
     * 多对多级联删除
     * */
    @Test
    public void delete(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            //删除用户 9 ，则用户 9 相关的角色也会被删除
            User u9 = session.get(User.class,17);
            session.delete(u9);


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
     * 为数据库中的用户和角色 建立 联系
     * */
    @Test
    public void add(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            User user = session.get(User.class,18);
            Role role = session.get(Role.class,18);

            user.getRoleSet().add(role);


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
     * 为用户删除某一个角色
     * */
    @Test
    public void remove(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            /* CRUD */
            User user = session.get(User.class,18);
            Role role = session.get(Role.class,18);

            user.getRoleSet().remove(role);


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
