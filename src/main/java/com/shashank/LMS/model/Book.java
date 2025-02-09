package com.shashank.LMS.model;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Book {
	private int id;
	private String name;
	private Genre genre;
	Author author;
	Card card;
	private boolean available;
	private List<Transaction> transactions;
	
}
