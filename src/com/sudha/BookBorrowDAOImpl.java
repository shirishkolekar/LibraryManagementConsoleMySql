package com.sudha;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class BookBorrowDAOImpl implements BookBorrowDAO {
	static UserDAO userDAO = new UserDAOImpl();
	static SubscriptionDAO subscriptionDAO = new SubscriptionDAOImpl();
	static ArrayList<BookBorrow> booksBorrow = new ArrayList<BookBorrow>();
	static ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();
	static Connection con;

	static PreparedStatement ps, ps1;
	static ResultSet rs;

	@Override

	public boolean borrowBook(int userId) {
		boolean status = false;
		BookBorrow bookborrow = new BookBorrow();
		try {
			ps = con.prepareStatement(
					"insert into BookBorrow(userId,bookId,borrowDate,returnDate,borrowApproved)values(?,?,?,?,?)");

			ps.setInt(1, bookborrow.getUserId());
			ps.setLong(2, bookborrow.getBookId());
			ps.setDate(3, Date.valueOf(bookborrow.getBorrowDate()));
			ps.setDate(4, Date.valueOf(bookborrow.getReturnDate()));
			ps.setBoolean(5, bookborrow.isBorrowApproved());
			booksBorrow.add(bookborrow);
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
	public ArrayList<BookBorrow> ShowListOfBooksBorrowed() {

		BookBorrow bb = null;
		ArrayList<BookBorrow> borrowedBooks = new ArrayList<BookBorrow>();
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select* from BookBorrow");
			rs = ps.executeQuery();

			while (rs.next()) {
				bb = new BookBorrow();// by calling constructor we allot memory to object on heap memory
				bb.setBookBorrowId(rs.getInt("bookborrowId"));
				bb.setUserId(rs.getInt("userId"));
				bb.setBookId(rs.getInt("bookId"));
				bb.setBorrowDate(rs.getDate("borrowDate").toLocalDate());
				bb.setReturnDate(rs.getDate("returnDate").toLocalDate());
				bb.setBorrowApproved(rs.getBoolean("borrowApproved"));
				bb.setReturnApproved(rs.getBoolean("returnApproved"));
				borrowedBooks.add(bb);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return borrowedBooks;
	}

	/*
	 * Create method to retreive select bookBorrowId where borrowApproved=false as
	 * ArrayList. Create method in operations which would call above method.
	 * 
	 */@Override
	public ArrayList<BorrowedBookDetail> ShowListOfBooksBorrowDetails() {
		ArrayList<BorrowedBookDetail> booksToBeApproved = new ArrayList<BorrowedBookDetail>();
		BorrowedBookDetail bb = null;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement(
					"select bookborrow.bookborrowId, bookborrow.bookId, book.bookName from bookborrow Inner Join book On BookBorrow.bookId = Book.bookId");
			rs = ps.executeQuery();

			while (rs.next()) {
				bb = new BorrowedBookDetail();
				bb.setBookBorrowId(rs.getInt("bookBorrowId"));
				bb.setBookName(rs.getString("BookName"));
				bb.setUserId(rs.getInt("userId"));
				booksToBeApproved.add(bb);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return booksToBeApproved;
	}

//			System.out.print("Do you want to approve (y/n) : ");
//			char input=sc.next().charAt(0);
//			
//			if(input=='Y' || input=='y')//for approval
	public boolean approveBookBorrow(BookBorrow bb, int bookId, int userId, Scanner Sc) {
		boolean approvalStatus = false;
		try {
			Subscription subscription = subscriptionDAO.ShowSubscriptionByUserId(userId);
			// to check subscription validity to allow borrow.
			if (subscription.getValidity().isAfter(LocalDate.now())) {
				ps1 = con.prepareStatement("update BookBorrow set borrowApproved=? where bookId=?");

				ps.setBoolean(1, true);
				ps.setInt(2, bookId);
				int count = ps.executeUpdate();

				if (count == 1) {
					approvalStatus = true;// Have to complete the method.
				}
			} else// if subscription validity is over
			{
				subscriptionDAO.deleteSubscription(subscription.getSubscriptionId());
				System.out.println("Subscription expired..Please renew to avail the facility!");
			}

//			 	else//for rejection
//			{
//				booksBorrow.remove(bb);
//				System.out.print("Payment failed. Please try again!");
//			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();

		}
		return approvalStatus;
	}

	@Override
	public boolean returnBook(int bookBorrowId) {
		boolean returnStatus = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("update BookBorrow set returnApproved=1 where bookBorrowId=? ");
			// ps.setDate(1, Date.valueOf(bookBorrow.getReturnDate().now()));
			ps.setBit(1, 1);// need to check from sir.
			ps.setInt(2, bookBorrowId);
			int count = ps.executeUpdate();

			if (count == 1) {
				returnStatus = true;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return returnStatus;
	}

	@Override
	public ArrayList<BookBorrow> listOfBooksToBeReturned(BookBorrow bookBorrow) {
		ArrayList<BookBorrow> listForReturnApproval = new ArrayList<BookBorrow>();
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select bookBorrowId from BookBorrow where returnApproved=1");
			rs = ps.executeQuery();

			while (rs.next()) {
				bookBorrow.setBookBorrowId(rs.getInt("bookBorrowId"));
				// bookBorrow.setReturnDate(rs.getDate("LocalDate.now()").toLocalDate());
				listForReturnApproval.add(bookBorrow);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return listForReturnApproval;
	}

	@Override
	public boolean approveReturn(int bookBorrowId) {
		boolean status = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("update BookBorrow set returnDate=? where bookBorrowId=? ");
			ps.setDate(1, Date.valueOf(LocalDate.now()));
			ps.setInt(2, bookBorrowId);

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
}
