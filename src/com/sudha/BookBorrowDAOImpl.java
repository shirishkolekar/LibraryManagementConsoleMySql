package com.sudha;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookBorrowDAOImpl implements BookBorrowDAO {
	static UserDAO userDAO = new UserDAOImpl();
	ArrayList<BookBorrow> booksBorrow = new ArrayList<BookBorrow>();

	static Connection con;

	static PreparedStatement ps,ps1;
	static ResultSet rs;

	@Override

	public boolean borrowBook(User u) {
		boolean status = false;
		BookBorrow bookborrow = new BookBorrow();
		try {
			if (userDAO.isUserSubscribed(u.getEmailId())) {
				con = DbConnection.getCon();
				ps = con.prepareStatement(
						"insert into BookBorrow(userId,bookId,bookName,borrowDate,returnDate,borrowApproved)values(?,?,?,?,?,?)");

				ps.setInt(1, bookborrow.getUserId());
				ps.setLong(2, bookborrow.getBookId());
				ps.setString(3, bookborrow.getBookName());
				ps.setDate(4, Date.valueOf(bookborrow.getBorrowDate()));
				ps.setDate(5, Date.valueOf(bookborrow.getReturnDate()));
				ps.setBoolean(6, bookborrow.isBorrowApproved());
			} else {
				System.out.println("Please subscribe first to avail the services!");
			}
			int count = ps.executeUpdate();

			if (count == 1) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public ArrayList<BookBorrow> ShowListOfBooksBorrowed(BookBorrow bb) {

		ArrayList<BookBorrow> borrowedBooks= new ArrayList<BookBorrow>();
		try
		{
			con=DbConnection.getCon();
			ps=con.prepareStatement("select* from BookBorrow");
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				bb.setBookBorrowId(rs.getInt("bookborrowId"));
				bb.setUserId(rs.getInt("userId"));
				bb.setBookId(rs.getInt("bookId"));
				bb.setBookName(rs.getString("bookName"));
				bb.setBorrowDate(rs.getDate("borrowDate").toLocalDate());
				bb.setReturnDate(rs.getDate("returnDate").toLocalDate());
				bb.setBorrowApproved(rs.getBoolean("borrowApproved"));
				bb.setReturnApproved(rs.getBoolean("returnApproved"));
				borrowedBooks.add(bb);
			}
		}
		catch (Exception e) 
		{
			System.out.println(e);
			e.printStackTrace();
		}
		return borrowedBooks;
	}

	@Override
	public boolean returnBook(BookBorrow bb, int bookId) {
		boolean returnStatus=false;
		
			return returnStatus;
	
		}

	@Override
	public boolean approveBookBorrow(BookBorrow bb, int bookId) {
		boolean BorrowApproval=false;
		try 
		{
			
			con=DbConnection.getCon();
			ps=con.prepareCall("Select bookId where borrowApproved=false");
			rs=ps.executeQuery();
			
	//	while(rs.next())
				System.out.println(bb.setBookId(rs.getInt("bookId"))+" "+ bb.setBorrowApproved(rs.getBoolean//("Approved")));
			ps1=con.prepareStatement("update BookBorrow set borrowApproved=true, borrowDate=localDate.now, returnDate=LocalDate.now.plusMonth(1) where bookId=?);"
				ps.setInt(1, bookId);
				
		ps.set
				
		}
		catch (Exception e) 
		{
			System.out.println(e);
			e.printStackTrace();
		}
		
			return BorrowApproval;
	
	}
}
