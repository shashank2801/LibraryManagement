package com.shashank.LMS.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
	
	
	public ResponseEntity<?> getById(int id) {
		Card card = cardRepository.findById(id).get();
		if(card==null)
			return new ResponseEntity<>("No card found by this id",HttpStatus.NOT_FOUND);
		else {
			List<Transaction> list = transactionRepository.findByCard(card);
			if(list!=null || list.size()!=0)
				return new ResponseEntity<>(list,HttpStatus.OK);
			else
				return new ResponseEntity<>("No transactions from this card",HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> getAll() {
		List<Transaction> list = transactionRepository.findAll();
		if(list!=null && list.size()!=0) {
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("No transactions found",HttpStatus.NO_CONTENT);
	} 
	
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
		book.setCardId(cardId);
		//list of books mapped in card
		List<Integer> books = card.getBooks();
		//adding books to card
		books.add(book.getId());
		//updating card
		card.setBooks(books);
		bookRepository.save(book);
		cardRepository.save(card);
		
		//new Transaction
		Transaction transaction = new Transaction();
		transaction.setCardId(cardId);
		transaction.setBookId(bookId);
		transaction.setIsIssueOperation(true);
		transaction.setTransactionStatus("successful");
		transactionRepository.save(transaction);
		return transaction.getTransactionId();
	}
	
	public String returnBook(int cardId,int bookId) {
		List<Transaction> transactions = transactionRepository.findByCardAndBook(cardId,bookId,"successful",true);
		Transaction lastTransaction = transactions.get(transactions.size()-1);
		Date issueDate = lastTransaction.getTransactionDate();
		Long issueTime = Math.abs(issueDate.getTime()-System.currentTimeMillis());
		Long days_passed = TimeUnit.DAYS.convert(issueTime,TimeUnit.MILLISECONDS);
		int fine = 0;
		if(days_passed>max_days_allowed) {
			fine = (int)Math.abs(days_passed-max_days_allowed)*fine_per_day;
		}
		
		Book book = bookRepository.findById(bookId).get();
		
		//reset
		book.setCardId(-1);
		book.setAvailable(true);
		//save
		bookRepository.save(book);
		
		//transaction
		Transaction transaction = new Transaction();
		transaction.setBookId(bookId);
		transaction.setCardId(cardId);
		transaction.setFineAmount(fine);
		transaction.setIsIssueOperation(false);
		transaction.setTransactionStatus("successful");
		transactionRepository.save(transaction);
		
		return transaction.getTransactionId();
	}
	
}
