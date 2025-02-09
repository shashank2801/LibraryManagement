package com.shashank.LMS.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ValidationMode;

@Entity
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "card",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Student student;
	
	@CreationTimestamp
	private Date createdOn;
	
	@UpdateTimestamp
	private Date updatedOn;
	
	@Enumerated(value=EnumType.STRING)
	private CardStatus cardStatus;
	
	@OneToMany(mappedBy="card",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Transaction> transactions;
	
	@OneToMany(mappedBy="card",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Book> books;
	
	
	
	public Card() {
		
	}
	
	

	public Card(int id, Student student, Date createdOn, Date updatedOn, CardStatus cardStatus,
			List<Transaction> transactions, List<Book> books) {
		this.id = id;
		this.student = student;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.cardStatus = cardStatus;
		this.transactions = transactions;
		this.books = books;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public CardStatus getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(CardStatus cardStatus) {
		this.cardStatus = cardStatus;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
}
