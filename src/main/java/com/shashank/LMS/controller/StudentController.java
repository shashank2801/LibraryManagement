package com.shashank.LMS.controller;

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

import com.shashank.LMS.model.Student;
import com.shashank.LMS.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/createStudent")
	public ResponseEntity<String> createStudent(@RequestBody Student student) {
		studentService.createStudent(student);
		return new ResponseEntity<String>("Student created",HttpStatus.CREATED);
	}
	
	@GetMapping("/getStudents")
	public ResponseEntity<?> getAllStudents(){
		ResponseEntity<?> list = studentService.getAllStudents();
		return list;
		
	}
	
	@PutMapping("/updateStudent")
	public ResponseEntity<String> updateStudent(@RequestParam int id,@RequestBody Student student) {
		ResponseEntity<String> res = studentService.updateStudent(id,student);
		return res;
	}
	
	@DeleteMapping("/deleteStudent")
	public ResponseEntity<String> deleteStudent(@RequestParam int studentId) {
		ResponseEntity<String> res = studentService.deleteStudent(studentId);
		return res;
	}
}
