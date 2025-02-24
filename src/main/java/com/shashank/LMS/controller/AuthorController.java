package com.shashank.LMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shashank.LMS.model.Author;
import com.shashank.LMS.repository.AuthorRepository;
import com.shashank.LMS.security.User;
import com.shashank.LMS.security.UserServiceImpl;
import com.shashank.LMS.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	

	@PostMapping("/createAuthor")
	public ResponseEntity<String> createAuthor(@RequestBody Author author) {
		authorService.createAuthor(author);
		return new ResponseEntity<String>("Author Created",HttpStatus.CREATED);
	}
	
	@PutMapping("/updateAuthor")
	public ResponseEntity<String> updateAuthor(@RequestBody Author author) {
		authorService.updateAuthor(author);
		return new ResponseEntity<String>("Author Updated",HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteAuthor")
	public ResponseEntity<String> deleteAuthor(@RequestParam("id") int id) {
		authorService.deleteAuthor(id);
		return new ResponseEntity<String>("Author Deleted",HttpStatus.ACCEPTED);
	}
	
}
