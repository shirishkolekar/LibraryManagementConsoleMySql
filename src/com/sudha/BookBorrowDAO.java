package com.sudha;

import java.util.ArrayList;
import java.util.Scanner;

public interface BookBorrowDAO{

	boolean borrowBook(User u);
	ArrayList<BookBorrow> ShowListOfBooksBorrowed(BookBorrow bb);
	boolean approveBookBorrow(BookBorrow bb, int bookId, Scanner Sc);
	boolean returnBook(BookBorrow bookBorrow, int bookId );
}


