package com.adrianbarczuk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.adrianbarczuk.hibernate.demo.entity.Course;
import com.adrianbarczuk.hibernate.demo.entity.Instructor;
import com.adrianbarczuk.hibernate.demo.entity.InstructorDetail;
import com.adrianbarczuk.hibernate.demo.entity.Review;
import com.adrianbarczuk.hibernate.demo.entity.Student;


public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start transaction
			session.beginTransaction();
			
			//create a course 
			Course course = new Course("Analiza Matematyczna");
			
			//save the course
			session.save(course);
			
			//create and add students to course
			Student firstStudent = new Student("Jan", "Kowalski", "jkowalski@mail.com");
			Student secondStudent = new Student("Jozef", "Nowak", "jnowak@mail.com");
			course.addStudent(firstStudent);
			course.addStudent(secondStudent);
			
			//save students
			session.save(firstStudent);
			session.save(secondStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
		
	}

}
