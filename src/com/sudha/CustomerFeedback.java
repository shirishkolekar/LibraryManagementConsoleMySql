package com.sudha;

public class CustomerFeedback {

	private int customerFeedbackId;
	private int userId;
	private String comment;
	private String feedbackDate;
	private boolean isApproved;
	private int bookid;

	public int getCustomerFeedbackId() {
		return customerFeedbackId;
	}

	public void setCustomerFeedbackId(int customerFeedbackId) {
		this.customerFeedbackId = customerFeedbackId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(String feedbackDate) {
		this.feedbackDate = feedbackDate;
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
		this.customerFeedbackId = 0;
		this.userId = 0;
		this.comment = "";
		this.feedbackDate = null;
		this.isApproved = false;
		this.bookid = 0;
	}

	public CustomerFeedback(int customerFeedbackId, int userId, String comment, String feedbackDate, boolean isApproved,
			int bookid) {
		this.customerFeedbackId = customerFeedbackId;
		this.userId = userId;
		this.comment = comment;
		this.feedbackDate = feedbackDate;
		this.isApproved = isApproved;
		this.bookid = bookid;
	}	
}