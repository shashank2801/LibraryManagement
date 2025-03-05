package com.shashank.LMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shashank.LMS.model.Card;

public interface CardRepository extends JpaRepository<Card, Integer>{
	
	@Query(value="update Card c set c.cardStatus = 'DEACTIVATED' where c.studentId = :student_id")
	public void deactivateCard(@Param("student_id") int studentId);
}
