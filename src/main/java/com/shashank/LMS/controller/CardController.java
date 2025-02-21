package com.shashank.LMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shashank.LMS.service.CardService;

@Controller
public class CardController {
	
	@Autowired
	private CardService cardService;
	
	@PostMapping("/createCard")
	public ResponseEntity<?> createCard(@RequestParam int id){
		return cardService.createCard(id);
	}
}
