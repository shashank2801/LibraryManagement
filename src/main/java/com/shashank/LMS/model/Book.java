package com.shashank.LMS.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String genre;

	private int authorId;
	
    private int cardId;
	
	@Column(columnDefinition = "TINYINT(1)")
	private boolean available;
	
	private List<Integer> transactions;
	
	public Book() {

	}
	
	
	public Book(String name, String genre, int authorId) {
		this.name = name;
		this.genre = genre;
		this.authorId = authorId;
		this.available = true;
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

//	public Author getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(Author author) {
//		this.author = author;
//	}
//
//	public Card getCard() {
//		return card;
//	}
//
//	public void setCard(Card card) {
//		this.card = card;
//	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public List<Integer> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Integer> transactions) {
		this.transactions = transactions;
	}


	public int getAuthorId() {
		return authorId;
	}


	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getCardId() {
		return cardId;
	}


	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	
	
}
