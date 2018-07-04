package com.adrianbarczuk.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.adrianbarczuk.hibernate.demo.entity.Customer;

public class QueryCustomerDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Customer.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start transaction
			session.beginTransaction();
			
			//query customers
			List<Customer> customers = session.createQuery("from Customer").getResultList();
			
			//display customers
			displayStudents(customers);
			
			//query: lastName = Kowalski
			customers = session.createQuery("from Customer c where c.lastName='Kowalski'").getResultList();
			
			//display customers
			displayStudents(customers);
			
			//query: lastName = Kowalski or firstName = Kaczor
			customers = session.createQuery("from Customer c where c.lastName='Kowalski' OR c.firstName='Kaczor'").getResultList();
			
			//display customers
			displayStudents(customers);
			
			//query: email ends with 'jiksinski.pl'
			customers = session.createQuery("from Customer c where c.email LIKE '%jiksinski.pl'").getResultList();
			
			//display customers
			displayStudents(customers);
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
		
	}

	private static void displayStudents(List<Customer> customers) {
		for (Customer customer : customers) {
			System.out.println(customer);
		}
	}

}
