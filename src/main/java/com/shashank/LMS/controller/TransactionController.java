package com.shashank.LMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shashank.LMS.service.TransactionService;

@Controller
@RequestMapping("/transaction")
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
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		ResponseEntity<?> status = transactionService.getAll();
		return status; 
	}
	
	@GetMapping("/byCard/{id}")
	public ResponseEntity<?> getByCard(@PathVariable int id) {
		ResponseEntity<?> status = transactionService.getById(id);
		return status;
	}
}
