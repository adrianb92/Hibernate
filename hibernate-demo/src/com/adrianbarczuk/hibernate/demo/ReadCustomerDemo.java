package com.adrianbarczuk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.adrianbarczuk.hibernate.demo.entity.Customer;

public class ReadCustomerDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Customer.class)
				.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();

		try {
			//use session object to save and retrieve object

			//create customer object
			System.out.println("Creating a new customer object...");
			Customer customer = new Customer("Kaczor", "Donald", "kdonald@jkdonald.pl");

			//start transaction
			session.beginTransaction();

			//save the object
			System.out.println("Saving the customers...");
			session.save(customer);

			//commit transaction
			session.getTransaction().commit();
			
			//check customer id - primary key
			System.out.println(customer.getId());
			
			//get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve customer by id
			Customer customer2 = session.get(Customer.class, customer.getId());
			System.out.println(customer2);
			
			//commit transaction
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		
	}

}
