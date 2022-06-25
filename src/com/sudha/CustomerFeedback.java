package com.sudha;

public class CustomerFeedback {
	
	private long readerID;
	private String comment; 
	private String date;
	private boolean isApproved;
	private int bookid;
	
	public long getReaderID() {
		return readerID;
	}
	public void setReaderID(long readerID) {
		this.readerID = readerID;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public CustomerFeedback() {

	}
	public CustomerFeedback(long readerID, String comment, String date, boolean isApproved, int bookid) {
		super();
		this.readerID = readerID;
		this.comment = comment;
		this.date = date;
		this.isApproved = isApproved;
		this.bookid = bookid;
	}
}



