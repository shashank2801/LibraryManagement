package com.shashank.LMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shashank.LMS.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
}
