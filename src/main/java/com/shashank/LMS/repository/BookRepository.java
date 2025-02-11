package com.shashank.LMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shashank.LMS.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	public List<Book> findByGenre(String genre);
	
	@Query("select b from Book b where b.genre = :genre and b.author in (select a from Author a where a.name = :author)")
	public List<Book> findByGenreAndAuthor(@Param("genre") String genre,@Param("author") String author);
	
	@Query("select b from Book b where b.available = :isAvailable")
	public List<Book> findByAvailable(@Param("isAvailable") boolean isAvailable);
	
	@Query("select b from Book b where b.author in (select a from Author a where a.name = :author)")
	public List<Book> findByAuthor(@Param("author") String author);
	
}
