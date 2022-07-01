package com.sudha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDAOImpl implements BookDAO {
	static Connection con;
	static PreparedStatement ps;
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
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}
		return books;
	}

	@Override
	public boolean addBook(Book book) {
		boolean status = false;
		try {
			con = DbConnection.getCon();
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
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
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
		}

		return status;
	}

	@Override
	public boolean deleteBook(int bookId) {
		boolean status = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("delete from books where bookId = ?");
			ps.setInt(1, bookId);
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
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return b;
	}
}
//select * from book where bookName= 'A' or author='b' or genre='c';....exact match
//select * from book where bookName like '%A%' or author like'%b%' or genre like'%b%;...partial match