package com.shashank.LMS.model;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String transactionId = UUID.randomUUID().toString();
	
	@ManyToOne
	@JoinColumn
	private Card card;
	
	private int fineAmount;
	
	@ManyToOne
	@JoinColumn
	private Book book;
	
	@Column(columnDefinition = "TINYINT(1)")
	private Boolean isIssueOperation;
	
	private String transactionStatus;
	
	@CreationTimestamp
	private Date transactionDate;
	
	

	public Transaction() {
	}

	public Transaction(Card card,Book book, Boolean isIssueOperation,
			String transactionStatus, Date transactionDate) {
		this.card = card;
		this.book = book;
		this.isIssueOperation = isIssueOperation;
		this.transactionStatus = transactionStatus;
		this.transactionDate = transactionDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public int getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(int fineAmount) {
		this.fineAmount = fineAmount;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Boolean getIsIssueOperation() {
		return isIssueOperation;
	}

	public void setIsIssueOperation(Boolean isIssueOperation) {
		this.isIssueOperation = isIssueOperation;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String string) {
		this.transactionStatus = string;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
}
