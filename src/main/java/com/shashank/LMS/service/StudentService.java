package com.shashank.LMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.shashank.LMS.model.Student;
import com.shashank.LMS.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	
	public void createStudent(Student student) {
		studentRepository.save(student);
	}
	
	public ResponseEntity<?> getAllStudents(){
		List<Student> listAll = studentRepository.findAll();
		if(listAll!=null && listAll.size()!=0)
			return new ResponseEntity<List<Student>>(listAll,HttpStatus.OK);
		else
			return new ResponseEntity<String>("No Student Present",HttpStatus.NO_CONTENT);
			
	}
	public ResponseEntity<String> updateStudent(int id,Student student) {
		try {
			
			Student old = studentRepository.findById(id).get();
			
			old.setAge(student.getAge());
			old.setCard(student.getCard());
			old.setCountry(student.getCountry());
			old.setEmail(student.getEmail());
			old.setName(student.getName());
			studentRepository.save(old);
			return new ResponseEntity<String>("Student Updated",HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("No student found with this id",HttpStatus.NOT_FOUND);
		}
		
	}
	
	public void deleteStudent(int id) {
		Student find = studentRepository.findById(id).get();
		if(find!=null) {
			studentRepository.delete(find);
		}
	}
}
