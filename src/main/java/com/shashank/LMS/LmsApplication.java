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
public class LmsApplication{

	public static void main(String[] args){
		SpringApplication.run(LmsApplication.class, args);
	}


}
