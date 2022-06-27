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
	static ArrayList<BookBorrow> booksBorrow = new ArrayList<BookBorrow>();
	static ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();
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
					booksBorrow.add(bookborrow);
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

		return status;
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
		
	//	con=DbConnection.getCon(); have to finish this method.
		//ps=con.prepareStatement()
		
			return returnStatus;
	
		}

	@Override
	public boolean approveBookBorrow(BookBorrow bb, int bookId, Scanner sc ) {
		boolean BorrowApproval=false;
		Subscription subscription=new Subscription();
		try 
		{
			con=DbConnection.getCon();
			ps=con.prepareStatement("select bookBorrowId where borrowApproved=false");
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				bb.setBookBorrowId(rs.getInt("bookBorrowId"));
				System.out.println(bb.getBookBorrowId());
			}
		
			System.out.print("Do you want to approve (y/n) : ");
			char input=sc.next().charAt(0);
			
			if(input=='Y' || input=='y')//for approval
			{
			 if(subscription.getValidity().isAfter(LocalDate.now()))//to check subscription validity to allow borrow.
			 {
				ps1=con.prepareStatement("update BookBorrow set borrowApproved=?, borrowDate=?, returnDate=? where 				bookId=?");
				
					ps.setBoolean(1, true);//Need to check from Sir
					ps.setDate(2, Date.valueOf(bb.getBorrowDate()));
					ps.setDate(3, Date.valueOf(bb.getReturnDate()));
					ps.setInt(4, bb.getBookBorrowId());
					int count =ps.executeUpdate();
				
					if(count==1)
					{
						BorrowApproval=true;//Have to complete the method.
					}
			 }
			 else//if subscription validity is over  
			 {
				 subscriptions.remove(subscription);
				 System.out.println("Subscription expired..Please renew to avail the facility!");
			 }
			}
			else//for rejection
			{
				booksBorrow.remove(bb);
				System.out.print("Payment failed. Please try again!");
			}
		}
		catch (Exception e) 
		{
			System.out.println(e);
			e.printStackTrace();
		}
			return BorrowApproval;
	}
}
