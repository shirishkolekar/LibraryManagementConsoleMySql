package com.sudha;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public interface BookBorrowDAO{

	boolean borrowBook(int userId);
	boolean approveBookBorrow(BookBorrow bb,int userId, int bookId, Scanner Sc);
	boolean returnBook(int bookBorrowId);
	boolean approveReturn(int bookBorrowId);
	ArrayList<BookBorrow> ShowListOfBooksBorrowed();
	ArrayList<BorrowedBookDetail> ShowListOfBooksBorrowDetails();
	ArrayList<BookBorrow> listOfBooksToBeReturned(BookBorrow bookBorrow);
	
	
}


