package com.shashank.LMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shashank.LMS.model.Card;
import com.shashank.LMS.model.Transaction;

import jakarta.transaction.Transactional;

@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	@Query("select t from Transaction t where t.cardId IN (select id from Card c where c.id = :cardId) and t.bookId IN (select id from Book b where b.id = :bookId) and t.transactionStatus = :status and t.isIssueOperation = :isIssue")
	List<Transaction> findByCardAndBook(@Param("cardId") int cardId,@Param("bookId") int bookId,@Param("status") String status,@Param("isIssue") boolean isIssue);

	@Query("select t from Transaction t where t.cardId = :cardId")
	List<Transaction> findByCard(@Param("cardId") int id);
}
