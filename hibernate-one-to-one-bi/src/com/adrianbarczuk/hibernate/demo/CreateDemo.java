package com.adrianbarczuk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.adrianbarczuk.hibernate.demo.entity.Instructor;
import com.adrianbarczuk.hibernate.demo.entity.InstructorDetail;


public class CreateDemo {

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
			
			//create the objects
			Instructor instructor =
					new Instructor("Adam", "Milosz", "mail@mail.pl");
			
			InstructorDetail instructorDetail = 
					new InstructorDetail(
							"youtubeAccount",
							"programming");
			
			//associate the object
			instructor.setInstructorDetail(instructorDetail);
			
			//start transaction
			session.beginTransaction();
				
			//save the instructor
			//also save instructorDetails
			//because of CascadeType.ALL
			session.save(instructor);
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
		
	}

}
