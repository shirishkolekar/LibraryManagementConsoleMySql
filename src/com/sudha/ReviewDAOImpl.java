package com.sudha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewDAOImpl implements ReviewDAO {
	boolean status = false;

	static Connection con;
	static PreparedStatement ps;
	static ResultSet rs;

	@Override
	public boolean review(int userId, int bookId, int stars, String comment) {
		Review review = new Review();
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("insert into Review(userId, bookId,stars, comment,approved)values(?,?,?,?,?)");

			ps.setInt(1, userId);
			ps.setInt(2, review.getBookId());
			ps.setInt(3, review.getStars());
			ps.setString(4, review.getComment());
			ps.setBoolean(5, false);
			int count = ps.executeUpdate();
			if (count == 1) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ArrayList<Review> ListOfReviewsForApproval(Review review) {
		ArrayList<Review> reviewListforApproval = new ArrayList<Review>();
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("Select reviewId, stars, comment from Review where approved=false");
			rs = ps.executeQuery();

			while (rs.next()) {
				review.setReviewId(rs.getInt("reviewId"));
				review.setApproved(false);
				review.setReviewId(rs.getInt("stars"));
				review.setComment(rs.getString("comment"));
				reviewListforApproval.add(review);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reviewListforApproval;
	}

	@Override
	public boolean approveReview(int reviewId) {
		boolean status = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("update Review set approved=true where reviewId=?");
			ps.setInt(1, reviewId);
			ps.executeUpdate();
			int count = ps.executeUpdate();
			if (count == 1) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean reviewByBookId(int bookId) {
		boolean status = false;
		Review review = null;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("Select reviewId, stars, comment from Review where bookId=?");
			ps.setInt(1, bookId);
			rs = ps.executeQuery();

			if (rs.next()) {
				review = new Review();
				review.setReviewId(rs.getInt("reviewId"));
				review.setReviewId(rs.getInt("stars"));
				review.setComment(rs.getString("comment"));
				status = true;
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();

		}
		return status;
	}
}
