package com.sudha;

import java.util.ArrayList;

public interface BookDAO {	
	boolean addBook(Book book);
	boolean editBook(Book book);
	boolean deleteBook(int bookId);
	ArrayList<Book> getAllBooks();
	Book getBookById(int bookId);
	boolean isBookAlreadyExists(String bookName);
}