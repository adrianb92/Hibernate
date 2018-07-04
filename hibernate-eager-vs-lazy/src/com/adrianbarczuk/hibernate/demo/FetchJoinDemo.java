package com.adrianbarczuk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.adrianbarczuk.hibernate.demo.entity.Course;
import com.adrianbarczuk.hibernate.demo.entity.Instructor;
import com.adrianbarczuk.hibernate.demo.entity.InstructorDetail;


public class FetchJoinDemo {

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
			Query<Instructor> query =
					session.createQuery("select i from Instructor i " 
							+ "JOIN FETCH i.courses " 
							+ "where i.id=:instructorId",
							Instructor.class);
			
			query.setParameter("instructorId", 1);
			
			Instructor instructor = query.getSingleResult();
			
			//commit transaction
			session.getTransaction().commit();
			
			session.close();
			
			//this will work because JOIN FETCH in HQL query above
			System.out.println("Courses: " + instructor.getCourses());
			
		} finally {
			session.close();
			factory.close();
		}
		
	}

}
