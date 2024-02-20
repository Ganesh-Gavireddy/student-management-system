package com.springboot.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.sms.entity.Student;
import com.springboot.sms.ligin_registration.entity.User;
import com.springboot.sms.service.StudentService;

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) 
    {
        super();
        this.studentService = studentService;
    }

    // Handler method to handle List of students and return Model & View
    //show the list of students
    
    
    @GetMapping("/students")
    public String listStudents(Model model)
    {
    	model.addAttribute("students", studentService.getAllStudents());
        return "students.html";
    }
    @GetMapping("/students/new")
    public String createStudentForm(Model model)
    {
    	Student student = new Student();
    	model.addAttribute("student",student);	
    	return "create_student.html";
    }
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student)
    {
    	studentService.saveStudents(student);
		return "redirect:/students";
    	
    }
    @GetMapping("students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model)
    {
    	model.addAttribute("student", studentService.getStudentById(id));
    	return"edit_Student.html";
    }
    @PostMapping("students/{id}")
    public String updateStudent(@PathVariable Long id,@ModelAttribute("student") Student student, Model model)
    {
    	// Get student from database by Id
    	Student existingStudent = studentService.getStudentById(id);
    	existingStudent.setId(id);
    	existingStudent.setFirstName(student.getFirstName());
    	existingStudent.setLastName(student.getLastName());
    	existingStudent.setEmail(student.getEmail());
    	
    	// Save updated student object
    	studentService.updateStudent(existingStudent);
		return "redirect:/students";	
    }
    
    // handler method to handle delete student request
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) 
    {
    	studentService.deleteStudentById(id);
		return"redirect:/students";
	}
    @GetMapping("/about-us")
    public static String aboutUsPage()
    {
    	return "about us.html";
    }
//    @GetMapping("/about-us")
//    public static String aboutUs() {
//       // String imageUrl = "/images/4360069_2312023.jpg"; // Relative path to the image
//       // model.addAttribute("imageUrl", imageUrl);
//        return "about_us.html"; // Thymeleaf template name
//    }
  }
