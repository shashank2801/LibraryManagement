package com.shashank.LMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shashank.LMS.security.User;
import com.shashank.LMS.security.UserServiceImpl;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/createAdmin")
	public void createAdmin(@RequestBody User user) {
		userService.saveUserAsAdmin(user);
	}
	
	@PostMapping("/createLibrarian")
	public void createLibrarian(@RequestBody User user) {
		userService.saveUserAsLibrarian(user);
	}
	
	@PostMapping("/createStudent")
	public void createStudent(@RequestBody User user) {
		userService.saveUserAsStudent(user);
	}
	
	@GetMapping("/all-users")
	public List<User> findAll(){
		return userService.findAll();
	}

}
