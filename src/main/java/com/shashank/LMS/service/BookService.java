package com.shashank.LMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shashank.LMS.model.Book;
import com.shashank.LMS.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	
	public void addBook(Book book) {
		bookRepository.save(book);
	}
	
	public List<Book> getBoooks(String genre,boolean isAvailable,String author){
		if(genre!=null && author!=null)
			return bookRepository.findByGenreAndAuthor(genre,author);
		else if(genre!=null)
			return bookRepository.findByGenre(genre);
		else if(author!=null)
			return bookRepository.findByAuthor(author);
		return bookRepository.findByAvailable(isAvailable);
	}
	
}
