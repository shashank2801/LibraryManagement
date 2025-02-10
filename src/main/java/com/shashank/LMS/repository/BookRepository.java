package com.shashank.LMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shashank.LMS.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	public void findByGenre(String genre);
	public void findByGenreAndAuthor(String genre, String author);
	
}
