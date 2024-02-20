package com.springboot.sms.service;



import com.springboot.sms.ligin_registration.entity.User;

public interface UserService
{
	User registerUser(User user);
}