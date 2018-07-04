package com.adrianbarczuk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.adrianbarczuk.hibernate.demo.entity.Instructor;
import com.adrianbarczuk.hibernate.demo.entity.InstructorDetail;


public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start transaction
			session.beginTransaction();
			
			int id = 3;
			InstructorDetail instructorDetail =
					session.get(InstructorDetail.class, id);
			
			System.out.println(instructorDetail.getInstructor());
			
			//commit transaction
			session.getTransaction().commit();
			
		} catch( Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		
	}

}
