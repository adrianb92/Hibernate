package com.adrianbarczuk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.adrianbarczuk.hibernate.demo.entity.Customer;

public class DeleteCustomerDemo {

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
			
//			//retrieve customer by id
//			Customer customer = session.get(Customer.class, studentId);
//			
//			//delete the customer
//			session.delete(customer);
//			
//			//commit transaction
//			session.getTransaction().commit();
			
			//delete customer with id = 2
			session.createQuery("delete from Customer where id=2").executeUpdate();
			
			//commit transaction
			session.getTransaction().commit();			

		} finally {
			factory.close();
		}
		
	}

}
