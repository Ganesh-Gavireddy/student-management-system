package com.springboot.sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException; // Import this exception
import org.springframework.stereotype.Service;

import com.springboot.sms.ligin_registration.entity.User;
import com.springboot.sms.repository.UserRepository;
import com.springboot.sms.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        try
        {
            return userRepository.save(user);
        }
        catch (DataIntegrityViolationException e) 
        {
        	System.out.println("This user name already exists..!");
            // Handle duplicate primary key error
            // You can log the error or return a custom error message
            // For simplicity, I'm returning null here. You can handle it as needed.
            return null;
        }
    }

    // Add other methods as needed

}
