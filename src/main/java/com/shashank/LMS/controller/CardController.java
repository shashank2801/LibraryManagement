package com.shashank.LMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shashank.LMS.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {
	
	@Autowired
	private CardService cardService;
	
	@PostMapping("/createCard")
	public ResponseEntity<?> createCard(@RequestParam int studentId){
		return cardService.createCard(studentId);
	}
}
