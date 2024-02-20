package com.springboot.sms.service;

import java.util.List;

import com.springboot.sms.entity.Student;

public interface StudentService 
{
	List<Student> getAllStudents();
	Student saveStudents(Student student);
	Student getStudentById(Long id);
	Student updateStudent(Student student);
	void deleteStudentById(Long id);
	
}
	
