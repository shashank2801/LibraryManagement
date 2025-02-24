package com.shashank.LMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shashank.LMS.security.User;
import com.shashank.LMS.security.UserServiceImpl;

@Controller
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/public/createAdmin")
	public void createAdmin(@RequestBody User user) {
		userService.saveUserAsAdmin(user);
	}
	
	@PostMapping("/public/createStudent")
	public void createStudent(@RequestBody User user) {
		userService.saveUser(user);
	}
	
	@GetMapping("/public")
	public List<User> findAll(){
		return userService.findAll();
	}

}
