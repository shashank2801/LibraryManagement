package com.shashank.LMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.shashank.LMS.model.Book;
import com.shashank.LMS.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/addBook")
	public ResponseEntity<String> addBook(@RequestBody Book book){
		ResponseEntity<String> status = bookService.addBook(book);
		return status;
//		return new ResponseEntity<String>("Book Added!",HttpStatus.CREATED);
	}
	
	@GetMapping("/getBooks")
	public ResponseEntity<List<Book>> getBooks(@RequestParam(value = "genre",required = false)String genre,
			@RequestParam(value="available",required = false,defaultValue = "true")boolean isAvailable,
			@RequestParam(value="author",required = false)String author){
		List<Book> bookList = bookService.getBoooks(genre, isAvailable, author);
		return new ResponseEntity<List<Book>>(bookList,HttpStatus.OK);
		
	}
		
}
