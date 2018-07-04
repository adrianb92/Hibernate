package com.adrianbarczuk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.adrianbarczuk.hibernate.demo.entity.Customer;

public class CreateCustomerDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Customer.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//use session object to save object
			
			//create an object
			System.out.println("Creating a new customer object...");
			Customer customer = new Customer("Jan", "Kowalski", "jkowalski@jkowalski.pl");
			
			//start transaction
			session.beginTransaction();
			
			//save the object
			System.out.println("Saving the customer...");
			session.save(customer);
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
		
	}

}
