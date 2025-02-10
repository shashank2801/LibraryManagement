package com.shashank.LMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shashank.LMS.model.Author;
import com.shashank.LMS.repository.AuthorRepository;

@Service
public class AuthorService {
	@Autowired
	private AuthorRepository authorRepository;
	
	public void createAuthor(Author author) {
		authorRepository.save(author);
	}
	
	public void updateAuthor(Author author) {
		Author old = authorRepository.findById(author.getId()).get();
		if(old!=null) {
			Author newAuth = author;
			authorRepository.save(newAuth);
		}
	}
	
	public void deleteAuthor(int id) {
		Author old = authorRepository.findById(id).get();
//		System.out.println("FOUND");
		if(old!=null) {
			authorRepository.delete(old);
		}
	}
}
