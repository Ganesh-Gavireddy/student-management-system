package com.springboot.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.springboot.sms.ligin_registration.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>
{
	List<User> findByEmailAndPassword(String email, String password);
	
	List<User> findByEmail(String email);
}
