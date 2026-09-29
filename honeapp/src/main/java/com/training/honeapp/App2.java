package com.training.honeapp;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
 
import com.training.model.DUser;
 
// find by id kind of thing
public class App2 
{
    public static void main( String[] args )
    {
    	// get a session factory
    	SessionFactory sessionFactory =
    			new Configuration().configure().buildSessionFactory();
    	// create a new session
    	Session session = sessionFactory.openSession();
    	DUser user = session.load(DUser.class, 2);
    	System.out.println(user);
    	session.close();
    }
}