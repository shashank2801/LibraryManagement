package com.shashank.LMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shashank.LMS.service.TransactionService;

@Controller
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/issueBook")
	public ResponseEntity<String> publishBook(@RequestParam(value = "cardId") int cardId, @RequestParam(value="bookId") int bookId) throws Exception {
		String transaction_id = transactionService.issueBook(cardId,bookId);
		return new ResponseEntity<String>("Transaction Successful with txn id#:" + transaction_id,HttpStatus.OK);
	}
	
	
	@PostMapping("/returnBook")
	public ResponseEntity<String> returnBook(@RequestParam(value = "cardId") int cardId, @RequestParam(value="bookId") int bookId) {
		String transaction_id = transactionService.returnBook(cardId,bookId);
		return new ResponseEntity<String>("Transaction Successful with txn id#:" + transaction_id,HttpStatus.OK);
	}
}
