package com.xiaowang;


import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.Iterator;
import java.util.List;

public class CriteriaTest {
    public void chaxun(Session session){
        Transaction transaction=session.beginTransaction();

        CarEntity carEntity = new CarEntity();
        carEntity.setId(104);
        carEntity.setName("xiaochao");

        session.save(carEntity);
        transaction.commit();
    }
    public static void main(String[]args){

        Configuration configuration=new Configuration().configure();
        SessionFactory sessionFactory=configuration.buildSessionFactory();
        Session session=sessionFactory.openSession();



        Criteria criteria =session.createCriteria(CarEntity.class);
        List list = criteria.list();

        Iterator iterator=list.iterator();
        while(iterator.hasNext()){
            CarEntity carEntity1= (CarEntity) iterator.next();
            System.out.println(carEntity1.getId()+"   "+carEntity1.getName());
        }

        Transaction transaction= session.beginTransaction();
        String sql ="update CarEntity set name = 'xiaowang' where id=:id";
        Query query =session.createQuery(sql);
        query.setParameter("id",100);
        query.executeUpdate();

        transaction.commit();

        criteria =session.createCriteria(CarEntity.class);
        criteria.add(Restrictions.eq("id",100));
        list =criteria.list();

        iterator =list.iterator();
        while(iterator.hasNext()){
            CarEntity carEntity1= (CarEntity) iterator.next();
            System.out.println(carEntity1.getId()+"   "+carEntity1.getName());
        }

    }
}
