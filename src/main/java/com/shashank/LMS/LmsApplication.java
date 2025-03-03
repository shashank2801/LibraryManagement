package com.shashank.LMS;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shashank.LMS.security.User;
import com.shashank.LMS.security.UserRepository;

@SpringBootApplication
public class LmsApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public static void main(String[] args){
		SpringApplication.run(LmsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setRoles(Arrays.asList("ADMIN"));
		userRepository.save(user);	
		
		
	}

}
