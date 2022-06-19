package com.sudha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDAOImpl implements BookDAO 
	{	
	static Connection con;
	static PreparedStatement ps;
	static ResultSet rs;
	
	@Override
	public ArrayList<Book> getAllBooks() {
		ArrayList<Book> books = new ArrayList<Book>();
		Book b = null;
		try
			{
			con = DbConnection.getCon();
			ps = con.prepareStatement("select * from books");
			rs = ps.executeQuery();
			while(rs.next())
				{
				b = new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setReview(rs.getInt("review"));
				b.setEdition(rs.getInt("edition"));
				b.setQuantitiy(rs.getInt("quantitiy"));
				b.setGenre(rs.getString("genre"));
				books.add(b);
				}
			con.close();
			}
		catch(Exception ex)
			{
			System.out.println(ex);
			ex.printStackTrace();
			}
		return books;
	}

	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBook(int bookId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Book getBookById(int bookId) {
		// TODO Auto-generated method stub
		return null;
	}
}