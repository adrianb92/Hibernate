package com.adrianbarczuk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.adrianbarczuk.hibernate.demo.entity.Course;
import com.adrianbarczuk.hibernate.demo.entity.Instructor;
import com.adrianbarczuk.hibernate.demo.entity.InstructorDetail;


public class EagerLazyDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start transaction
			session.beginTransaction();
			
			//get instructor from db
			Instructor instructor = session.get(Instructor.class,  1);
			
			//in lazy fetching courses are loading only on demand in this line below
			//in eager fetching courses are loading when instructor is called 
			System.out.println("Courses: " + instructor.getCourses());
			
			//commit transaction
			session.getTransaction().commit();
			
			session.close();
			
			//lazy fetching problem after closing session
			//this will work because courses were loaded before, while session was open
			System.out.println("Courses: " + instructor.getCourses());
			
		} finally {
			session.close();
			factory.close();
		}
		
	}

}
