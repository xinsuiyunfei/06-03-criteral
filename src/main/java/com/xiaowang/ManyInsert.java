package com.xiaowang;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManyInsert {
    public static void main(String[]args){
        Configuration configuration=new Configuration().configure();
        SessionFactory sessionFactory=configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();


        for (int i=1;i<100;i++){
            Transaction transaction=session.beginTransaction();
            CarEntity carEntity=new CarEntity();
            carEntity.setId(i);
            session.save(carEntity);
            transaction.commit();
        }
        session.close();
        sessionFactory.close();


    }
}
