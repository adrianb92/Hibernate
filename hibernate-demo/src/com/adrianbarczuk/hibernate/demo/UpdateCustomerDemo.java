package com.adrianbarczuk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.adrianbarczuk.hibernate.demo.entity.Customer;

public class UpdateCustomerDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Customer.class)
				.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();

		try {
			
			int studentId = 1;
			
			//start transaction
			session.beginTransaction();
			
			//retrieve customer by id
			Customer customer = session.get(Customer.class, studentId);
			System.out.println(customer);
			
			//update customer name to "Tomasz"
			customer.setFirstName("Tomasz");
			
			//commit transaction
			session.getTransaction().commit();
			
			//update all customers email to "test@test.com"
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.createQuery("update Customer set email='test@test.com'").executeUpdate();
			
			//commit transaction
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		
	}

}
