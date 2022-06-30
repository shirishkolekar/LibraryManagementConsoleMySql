package com.sudha;

import java.time.LocalDate;

public class BookBorrow {
	
	private int bookBorrowId;
	private int userId;
	private int bookId;
	//private String bookName;
	private LocalDate borrowDate;
	private LocalDate returnDate;
	private boolean borrowApproved;
	private boolean returnApproved;
	

	public int getBookBorrowId() {
		return bookBorrowId;
	}

	public void setBookBorrowId(int bookBorrowId) {
		this.bookBorrowId = bookBorrowId;
	}

//	//public String getBookName() {
//		return bookName;
//	}
//
//	//public void setBookName(String bookName) {
//		this.bookName = bookName;
//	}

	
	public boolean isBorrowApproved() {
		return borrowApproved;
	}

	public void setBorrowApproved(boolean borrowApproved) {
		this.borrowApproved = borrowApproved;
	}

	public boolean isReturnApproved() {
		return returnApproved;
	}

	public void setReturnApproved(boolean returnApproved) {
		this.returnApproved = returnApproved;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public BookBorrow() {
		this.bookBorrowId=0;
		this.userId = 0;
		this.bookId = 0;
		this.borrowDate = LocalDate.now();
		this.returnDate = null;
		this.borrowApproved=false;
		this.returnApproved=false;
	//	this.bookName = "";
	}

	public BookBorrow(int bookBorrowId, int userId, int bookId, LocalDate borrowDate, LocalDate returnDate,boolean borrowApproved,
			boolean returnApproved) {
		this.userId = userId;
		this.bookId = bookId;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.borrowApproved=borrowApproved;
		this.returnApproved=returnApproved;
//		this.bookName = bookName;
		this.bookBorrowId=bookBorrowId;
	}


}
