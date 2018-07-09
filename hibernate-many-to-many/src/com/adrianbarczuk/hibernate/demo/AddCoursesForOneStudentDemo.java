package com.adrianbarczuk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.adrianbarczuk.hibernate.demo.entity.Course;
import com.adrianbarczuk.hibernate.demo.entity.Instructor;
import com.adrianbarczuk.hibernate.demo.entity.InstructorDetail;
import com.adrianbarczuk.hibernate.demo.entity.Review;
import com.adrianbarczuk.hibernate.demo.entity.Student;


public class AddCoursesForOneStudentDemo {

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
			
			//add new course for student
			Student student = session.get(Student.class, 1);
			
			Course course = new Course("Fizyka");
			
			course.addStudent(student);
			
			session.save(course);
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
		
	}

}
