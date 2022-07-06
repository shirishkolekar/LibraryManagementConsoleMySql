package com.sudha;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookBorrowDAOImpl implements BookBorrowDAO {
	static UserDAO userDAO = new UserDAOImpl();
	static SubscriptionDAO subscriptionDAO = new SubscriptionDAOImpl();
	static ArrayList<BookBorrow> booksBorrow = new ArrayList<BookBorrow>();
	static ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();

	static Connection con;
	static PreparedStatement ps, ps1;
	static ResultSet rs;

	@Override

	public boolean borrowBook(int userId, int bookId) {
		boolean status = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("insert into BookBorrow(userId,bookId,borrowDate)values(?,?,?)");
			ps.setInt(1, userId);
			ps.setLong(2, bookId);
			ps.setDate(3, Date.valueOf(LocalDate.now()));
			int count = ps.executeUpdate();
			if (count == 1) {
				status = true;
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
		return status;
	}

	@Override
	public ArrayList<BookBorrow> ShowListOfBooksBorrowed() {

		BookBorrow bb = null;
		ArrayList<BookBorrow> borrowedBooks = new ArrayList<BookBorrow>();
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select * from BookBorrow");
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
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return booksToBeApproved;
	}

	@Override
	public boolean approveBookBorrow(int bookBorrowId) {
		boolean approvalStatus = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("update BookBorrow set borrowApproved=true where bookBorrowId=?");
			ps = con.prepareStatement("update BorrowedBookDetail set quantity=quantity-1 where bookBorrowId=?");
			ps.setInt(1, bookBorrowId);
			int count = ps.executeUpdate();
			if (count == 1) {

				approvalStatus = true;
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
		return approvalStatus;
	}

	// reader is returning book
	@Override
	public boolean returnBook(int bookBorrowId) {
		boolean returnStatus = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("update BookBorrow set returnDate = ? where bookBorrowId = ?");
			ps.setDate(1, Date.valueOf(LocalDate.now()));
			ps.setInt(2, bookBorrowId);
			int count = ps.executeUpdate();
			if (count == 1) {
				returnStatus = true;
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
				listForReturnApproval.add(bookBorrow);
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
		return listForReturnApproval;
	}

	@Override
	public boolean approveReturn(int bookBorrowId) {
		boolean status = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("update bookBorrow set returnApproved = 1 where bookBorrowId=? ");
			ps.setDate(1, Date.valueOf(LocalDate.now()));
			ps.setInt(2, bookBorrowId);
			int count = ps.executeUpdate();
			if (count == 1) {
				status = true;
			}
			count = 0;
			// get bookid
			int bookId = 0;
			ps = con.prepareStatement("select bookId from bookBorrow where bookBorrowId=? ");
			ps.setInt(1, bookBorrowId);
			rs = ps.executeQuery();
			if (rs.next()) {
				bookId = rs.getInt("bookId");
			}
			// get current quantity for book
			int quantity = 0;
			ps = con.prepareStatement("select quantity from Books where bookId=? ");
			ps.setInt(1, bookId);
			rs = ps.executeQuery();
			if (rs.next()) {
				quantity = rs.getInt("quantity");
			}
			// update new quantity
			count = 0;
			ps = con.prepareStatement("update Books set quantity=? where bookId=? ");
			ps.setInt(1, quantity + 1);
			ps.setInt(2, bookId);
			count = ps.executeUpdate();
			if (count == 1) {
				status = true;
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
		return status;
	}

	@Override
	public ArrayList<BorrowedBookDetail> ShowListOfBooksBorrowedByUser(int userId) {
		ArrayList<BorrowedBookDetail> booksBorrowed = new ArrayList<BorrowedBookDetail>();
		BorrowedBookDetail bb = null;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement(
					"select bookborrow.bookborrowId, bookborrow.bookId, book.bookName from bookborrow inner Join "
							+ "book On BookBorrow.bookId = Book.bookId where userId=?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				bb = new BorrowedBookDetail();
				bb.setBookBorrowId(rs.getInt("bookBorrowId"));
				bb.setBookName(rs.getString("BookName"));
				bb.setUserId(rs.getInt("userId"));
				booksBorrowed.add(bb);
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
		return booksBorrowed;
	}

	@Override
	public boolean deleteBookBorrow(int bookBorrowId) {
		boolean status = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("delete from BookBorrow where bookBorrowId=?");
			ps.setInt(1, bookBorrowId);
			int count = ps.executeUpdate();
			if (count == 1) {
				status = true;
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
		return status;
	}
}