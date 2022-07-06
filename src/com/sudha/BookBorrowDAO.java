package com.sudha;

import java.util.ArrayList;

public interface BookBorrowDAO {

	boolean borrowBook(int userId, int bookId);

	public boolean approveBookBorrow(int bookBorrowId);
	
	boolean returnBook(int bookBorrowId);

	boolean approveReturn(int bookBorrowId);

	ArrayList<BookBorrow> ShowListOfBooksBorrowed();

	ArrayList<BorrowedBookDetail> ShowListOfBooksBorrowDetails();

	ArrayList<BookBorrow> listOfBooksToBeReturned(BookBorrow bookBorrow);

	ArrayList<BorrowedBookDetail> ShowListOfBooksBorrowedByUser(int userId);

	boolean deleteBookBorrow(int bookBorrowId);
}