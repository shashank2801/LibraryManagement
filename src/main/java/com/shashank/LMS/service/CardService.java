package com.shashank.LMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shashank.LMS.model.Card;
import com.shashank.LMS.model.Student;
import com.shashank.LMS.repository.CardRepository;

@Service
public class CardService {
	
	@Autowired
	private CardRepository cardRepository;
	
	public Card createCard(Student student) {
		Card card = new Card();
		student.setCard(card);
		card.setStudent(student);
		cardRepository.save(card);
		return card;
	}
	
	public void deactivateCard(int studentId) {
		cardRepository.deactivateCard(studentId);
	}
}
