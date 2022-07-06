package com.sudha;

import java.util.ArrayList;

public interface ReviewDAO {
	
	boolean review(int userId,int bookId, int stars, String comment); 
	ArrayList<Review> ListOfReviewsForApproval(Review review);
	boolean approveReview(int reviewId );
	boolean reviewByBookId(int bookId);
}

