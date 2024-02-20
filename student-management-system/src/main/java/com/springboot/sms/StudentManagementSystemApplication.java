
package com.springboot.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.sms.entity.Student;
import com.springboot.sms.repository.StudentRepository;
import com.springboot.sms.repository.UserRepository;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner
{


	public static void main(String[] args)
	{
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	
		
		System.out.println("welcome to new springboot..");
		System.out.println("localhost : 8081");
		
		
	}
	@Autowired
	private StudentRepository studentRepository;
//	
//	@Autowired
//	private UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception 
	{
//		Student student1 = new Student("Ganesh", "Gavireddy","gani@gmail.com");
//		studentRepository.save(student1);	
	}

}
