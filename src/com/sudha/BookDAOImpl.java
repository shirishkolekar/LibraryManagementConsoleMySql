package com.sudha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAOImpl implements BookDAO {
	static Connection con;
	static PreparedStatement ps;
	static PreparedStatement ps1;
	static ResultSet rs;

	@Override
	public ArrayList<Book> getAllBooks() {
		ArrayList<Book> books = new ArrayList<Book>();
		Book b = null;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select * from books");
			rs = ps.executeQuery();
			while (rs.next()) {
				b = new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setReview(rs.getInt("review"));
				b.setEdition(rs.getInt("edition"));
				b.setQuantity(rs.getInt("quantity"));
				b.setGenreId(rs.getInt("genreId"));
				books.add(b);
			}
		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return books;
	}

	@Override
	public boolean isBookAlreadyExists(String bookName) {
		boolean status = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select * from books where bookName=?");
			ps.setString(1, bookName);
			rs = ps.executeQuery();
			if (rs.next()) {
				status = true;
			}
		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
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
	public boolean addBook(Book book) {
		boolean status = false;
		try {
			con = DbConnection.getCon();
			if (isBookAlreadyExists(book.getBookName())) {
				ps = con.prepareStatement("select quantity where bookName=?");
				ps.setString(1, book.getBookName());
				rs = ps.executeQuery();

				if (rs.next()) {
					ps = con.prepareStatement("update books set quantity=? where bookName=?");
					ps.setInt(1, book.getQuantity() + rs.getInt("quantity"));

				}
				int count = ps.executeUpdate();

				if (count == 1) {
					status = true;
				}
			} else {
				ps = con.prepareStatement(
						"insert into books(bookName,author,review,edition,quantitity,genreId) values(?,?,?,?,?,?)");

				ps.setString(1, book.getBookName());
				ps.setString(2, book.getAuthor());
				ps.setInt(3, book.getReview());
				ps.setInt(4, book.getEdition());
				ps.setInt(5, book.getQuantity());
				ps.setInt(6, book.getGenreId());
				int count = ps.executeUpdate();

				if (count == 1) {
					status = true;
				}
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
	public boolean editBook(Book book) {

		boolean status = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement(
					"update books set bookName = ?,author=?,review=?,edition=?,quantity=?,genreId=? where bookId = ?");
			ps.setString(1, book.getBookName());
			ps.setString(2, book.getAuthor());
			ps.setInt(3, book.getReview());
			ps.setInt(4, book.getEdition());
			ps.setInt(5, book.getQuantity());
			ps.setInt(6, book.getGenreId());
			ps.setInt(7, book.getBookId());

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
	public boolean deleteBook(int bookId) {
		boolean status = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("update books set active = 0 where bookId = ?");
			ps.setInt(1, bookId);
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
	public Book getBookById(int bookId) {

		Book b = new Book();
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select * from books where bookid=?");
			ps.setInt(1, bookId);
			rs = ps.executeQuery();

			if (rs.next()) {
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setReview(rs.getInt("review"));
				b.setEdition(rs.getInt("edition"));
				b.setQuantity(rs.getInt("quantity"));
				b.setGenreId(rs.getInt("genreId"));
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
		return b;
	}

	

//	@Override
//	public boolean review(String bookName, int review) {
//		boolean status = false;
//		try {
//			con = DbConnection.getCon();
//			ps = con.prepareStatement("insert into Book(review=?) where bookName=?");
//			ps.setInt(1, review);
//			ps.setString(2, bookName);
//			int count = ps.executeUpdate();
//			if (count == 1) {
//				status = true;
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
//		}
//		finally
//		{
//			try {
//				con.close();
//			} catch (SQLException e) {				
//				e.printStackTrace();
//			}	
//		}
//		return status;
//	}
}
//select * from book where bookName= 'A' or author='b' or genre='c';....exact match
//select * from book where bookName like '%A%' or author like'%b%' or genre like'%b%;...partial match