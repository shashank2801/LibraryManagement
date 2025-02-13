package com.shashank.LMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shashank.LMS.model.Book;
import com.shashank.LMS.model.Card;
import com.shashank.LMS.model.Transaction;
import com.shashank.LMS.repository.BookRepository;
import com.shashank.LMS.repository.CardRepository;
import com.shashank.LMS.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	@Value("${books.max_allowed}")
	int max_allowed_books;
	@Value("${books.max_allowed_days}")
	int max_days_allowed;
	@Value("${books.fine.per_day}")
	int fine_per_day;
	
	
	public String issueBook(int cardId, int bookId) throws Exception {
		Book book = bookRepository.findById(bookId).get();
		if(book==null || book.isAvailable()!=true)
			throw new Exception("Book is not available");
		
		Card card = cardRepository.findById(cardId).get();
		if(card==null || card.getCardStatus().equalsIgnoreCase("deactivated"))
			throw new Exception("Invalid Card");
		
		if(card.getBooks().size()>max_allowed_books)
			throw new Exception("Book limit reached for this Card");
		
		//updating book status
		book.setAvailable(false);
		//mapping book to card
		book.setCard(card);
		//list of books mapped in card
		List<Book> books = card.getBooks();
		//adding books to card
		books.add(book);
		//updating card
		card.setBooks(books);
		bookRepository.save(book);
		cardRepository.save(card);
		
		//new Transaction
		Transaction transaction = new Transaction();
		transaction.setCard(card);
		transaction.setBook(book);
		transaction.setIsIssueOperation(true);
		transaction.setTransactionStatus("successful");
		transactionRepository.save(transaction);
		return transaction.getTransactionId();
	}
	
	public String returnBook(int cardId,int bookId) {
		transactionRepository.save(null);
		return "";
	}
	
}
