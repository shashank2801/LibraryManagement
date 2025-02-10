package com.shashank.LMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shashank.LMS.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	public List<Book> findByGenre(String genre);
	public List<Book> findByGenreAndAuthor(String genre, String author);
	public List<Book> findByAvailable(boolean isAvailable);
	public List<Book> findByAuthor(String author);
	
}
