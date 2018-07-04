package com.adrianbarczuk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.adrianbarczuk.hibernate.demo.entity.Customer;

public class PrimaryKeyDemo {

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

			//create 3 customers object
			System.out.println("Creating a new customer object...");
			Customer customer1 = new Customer("Jan", "Kowalski", "jkowalski@jkowalski.pl");
			Customer customer2 = new Customer("Tomasz", "Nowak", "tnowak@tnowak.pl");
			Customer customer3 = new Customer("Jozef", "Iksinski", "jiksinski@jiksinski.pl");

			//start transaction
			session.beginTransaction();

			//save the objects
			System.out.println("Saving the customers...");
			session.save(customer1);
			session.save(customer2);
			session.save(customer3);

			//commit transaction
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		
	}

}
