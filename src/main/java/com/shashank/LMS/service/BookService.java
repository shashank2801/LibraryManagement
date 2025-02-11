package com.shashank.LMS.service;

import java.util.List;

import org.hibernate.grammars.hql.HqlParser.IsEmptyPredicateContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.shashank.LMS.model.Author;
import com.shashank.LMS.model.Book;
import com.shashank.LMS.model.Genre;
import com.shashank.LMS.repository.AuthorRepository;
import com.shashank.LMS.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepositoy;
	
	
//	public void addBook(Book book) {
//		bookRepository.save(book);
//	}
	
	public List<Book> getBoooks(String genre,boolean isAvailable,String author){
		if(genre!=null && author!=null)
			return bookRepository.findByGenreAndAuthor(genre,author);
		else if(genre!=null)
			return bookRepository.findByGenre(genre);
		else if(author!=null)
			return bookRepository.findByAuthor(author);
		return bookRepository.findByAvailable(isAvailable);
	}
	
	//transactional
	public void addBook(Book book) {
		System.out.println(book.getAuthId());
		Author auth = authorRepositoy.findById(book.getAuthId()).get();
		if(auth!=null) {
			Book newBook = new Book(book.getName(),book.getGenre(),auth);
			bookRepository.save(newBook);
			
		}
		
	}
	
}
