package com.sudha;

public class BorrowedBookDetail {

	private int bookBorrowId;
	private int userId;
	private int bookId;
	private String bookName;
	public int getBookBorrowId() {
		return bookBorrowId;
	}
	public void setBookBorrowId(int bookBorrowId) {
		this.bookBorrowId = bookBorrowId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public BorrowedBookDetail() {
		super();
		this.bookBorrowId = 0;
		this.userId = 0;
		this.bookId = 0;
		this.bookName = "";
	}

	public BorrowedBookDetail(int bookBorrowId, int userId, int bookId, String bookName) {
		super();
		this.bookBorrowId = bookBorrowId;
		this.userId = userId;
		this.bookId = bookId;
		this.bookName = bookName;
	}
}
