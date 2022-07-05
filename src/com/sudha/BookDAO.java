package com.sudha;

import java.util.ArrayList;

public interface BookDAO {	
	boolean addBook(Book book);
	boolean editBook(Book book);
	boolean deleteBook(int bookId);
	ArrayList<Book> getAllBooks();
	Book getBookById(int bookId);
	boolean isBookAlreadyExists(String bookName);
	boolean review(String bookName, int review);
	boolean approveReview(int bookId,String bookName, int userId);
	
}