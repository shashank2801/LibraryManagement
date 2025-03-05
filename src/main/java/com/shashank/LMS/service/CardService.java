package com.shashank.LMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shashank.LMS.model.Card;
import com.shashank.LMS.model.Student;
import com.shashank.LMS.repository.CardRepository;
import com.shashank.LMS.repository.StudentRepository;

@Service
public class CardService {
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public ResponseEntity<String> createCard(int id) {
		System.out.println("Service called");
		Card card = new Card();
		try {
			Student student = studentRepository.findById(id).get();
			card.setStudentId(student.getId());
			cardRepository.save(card);
			return new ResponseEntity<String>("Card mapped with student id:"+student.getId(),HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("Student not found with given id.",HttpStatus.NOT_FOUND);
		}

	}
	
	public void deactivateCard(int studentId) {
		cardRepository.deactivateCard(studentId);
	}
}
