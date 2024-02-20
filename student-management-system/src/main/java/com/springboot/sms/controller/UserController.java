package com.springboot.sms.controller;

import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.mysql.cj.x.protobuf.MysqlxResultset.FetchSuspendedOrBuilder;
import com.springboot.sms.entity.Student;
import com.springboot.sms.ligin_registration.entity.User;
import com.springboot.sms.repository.UserRepository;
import com.springboot.sms.service.StudentService;
import com.springboot.sms.service.UserService;

import jakarta.persistence.metamodel.SetAttribute;
@Controller
public class UserController 
{

	@Autowired
	private UserRepository userRepository;
	private UserService userService;
	@Autowired
	//private User user;
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	@RequestMapping("/login")
	public String userLogin()
	{
		return "ligin.html";
	}
	@RequestMapping("/registration")
	public String showregistrationForm()
	{
		return "registration.html";
	}
	 @PostMapping("/registration")
	    public String studentRegistrationForm(@ModelAttribute("users") User user, @RequestParam("email") String userEmail)
	    {
		  	 List<User> existinfEmail = userRepository.findByEmail(userEmail);
		 	if(existinfEmail.isEmpty())
		 	{
		 		userService.registerUser(user);
		    	return "redirect:/registration?success";
		 	}
		 	else
		 	{
		 		return "redirect:/registration?error";
		 	}
	    }
	 @PostMapping("/login/success")
	 public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
		    // Check if user exists in the database
		    List<User> existingDatabase = userRepository.findByEmailAndPassword(email, password);
		    
		    // Check if the list is empty or not
		    if (!existingDatabase.isEmpty()) {
		        // User exists, print user details and redirect to home page
		        User user = existingDatabase.get(0); // Assuming there's only one user with the given email and password
		        System.out.println(" *******************User details: **************************");
		        System.out.println("User FirstName : " + user.getFirstName());
		        System.out.println("User LastName : " + user.getLastName());
		        System.out.println("User Email: " + user.getEmail());
		        System.out.println("User Password : " + user.getPassword());
		        System.out.println("***********************************************************");

		        model.addAttribute("success", "User Successfully Logged in!");
		        // Add user details to the model if needed
		        model.addAttribute("sName", user.getEmail());
		        model.addAttribute("user", user);
		        String userfName = user.getFirstName();
		        String userlName = user.getLastName();
		        model.addAttribute("fname", userfName);
		        model.addAttribute("lname", userlName);
		        
		        return "home.html";
		    } else {
		        // User does not exist or incorrect credentials, display error message
		        model.addAttribute("error", "Invalid Credentials!");
		        return "redirect:/login?error";
		    }
		}
	 @GetMapping("/logout")
	 public static String logoutPage()
	 {
		return "redirect:/login?logout"; 
	 }
	 @GetMapping("/home")
	 public static String showHomePage()
	 {
		return "home.html";
	 }
	 
}
