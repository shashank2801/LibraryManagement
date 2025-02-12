package com.shashank.LMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shashank.LMS.model.Student;
import com.shashank.LMS.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	
	public void createStudent(Student student) {
		studentRepository.save(student);
	}
	
	public void updateStudent(Student student) {
		Student old = studentRepository.findById(student.getId()).get();
		if(old!=null) {
			Student updated = student;
			studentRepository.save(updated);
		}
	}
	
	public void deleteStudent(int id) {
		Student find = studentRepository.findById(id).get();
		if(find!=null) {
			studentRepository.delete(find);
		}
	}
}
