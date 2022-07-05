package com.sudha;

public class Review {

	int reviewId;
	int userId;
	int bookId;
	int stars;
	String comment;
	boolean approved;

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
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

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Review() {
		this.reviewId = 0;
		this.userId = 0;
		this.bookId = 0;
		this.stars = 0;
		this.comment = "";
		this.approved = false;
	}

	public Review(int reviewId, int userId, int bookId, int stars, String comment, boolean approved) {
		this.reviewId = reviewId;
		this.userId = userId;
		this.bookId = bookId;
		this.stars = stars;
		this.comment = comment;
		this.approved = approved;
	}
}