package com.sudha;

import java.util.ArrayList;
import java.util.Scanner;

public interface BookBorrowDAO{

	boolean borrowBook(User u);
	boolean approveBookBorrow(BookBorrow bb,int userId, int bookId, Scanner Sc);
	boolean returnBook(BookBorrow bookBorrow, int bookId );
	ArrayList<BookBorrow> ShowListOfBooksBorrowed();
	ArrayList<BorrowedBookDetail> ShowListOfBooksBorrowDetails();
}


