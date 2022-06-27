package com.sudha;

import java.util.ArrayList;

public interface BookBorrowDAO{

	boolean borrowBook(User u);
	ArrayList<BookBorrow> ShowListOfBooksBorrowed(BookBorrow bb);
	boolean approveBookBorrow(BookBorrow bb, int bookId);
	boolean returnBook(BookBorrow bookBorrow, int bookId );
}


