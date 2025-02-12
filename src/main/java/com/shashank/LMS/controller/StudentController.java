package com.shashank.LMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shashank.LMS.model.Student;
import com.shashank.LMS.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/createStudent")
	public ResponseEntity<String> createStudent(@RequestParam Student student) {
		studentService.createStudent(student);
		return new ResponseEntity<String>("Student created",HttpStatus.CREATED);
	}
	
	@PutMapping("/updateStudent")
	public ResponseEntity<String> updateStudent(@RequestParam Student student) {
		studentService.updateStudent(student);
		return new ResponseEntity<String>("Student updated",HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteStudent")
	public ResponseEntity<String> deleteStudent(@RequestParam int studentId) {
		studentService.deleteStudent(studentId);
		return new ResponseEntity<String>("Student deleted",HttpStatus.ACCEPTED);
	}
}
