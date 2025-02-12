package com.shashank.LMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shashank.LMS.model.Card;

public interface CardRepository extends JpaRepository<Card, Integer>{
	
	@Query(value="update card c set c.card_status = 'DEACTIVATED' where c.id in (select card_id from Student s where s.id = :student_id)")
	public void deactivateCard(@Param("student_id") int studentId);
}
