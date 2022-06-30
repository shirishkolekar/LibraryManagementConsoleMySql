package com.sudha;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.sudha.Library.LoginStatus;

public class Operation {
	UserDAO userDAO = new UserDAOImpl();

	ArrayList<User> users = new ArrayList<User>();
	BookBorrowDAO bookBorrowDAO=new BookBorrowDAOImpl();
	static Connection con;

	static PreparedStatement ps;
	static PreparedStatement ps1;
	static ResultSet rs;

	public boolean signUp(Scanner sc)// sign up
	{
		User user = new User();
		boolean signUpStatus = false;
		try {
			System.out.println("Email ID : ");

			user.setEmailId(Utilities.getInput());
			if (userDAO.isUserExists(user.getEmailId())) {
				System.out.println("Email already exists..try again!");
			} else {
				System.out.println("User's Name : ");
				user.setUserName(Utilities.getInput());

				System.out.println("Contact Number : ");
				user.setContactNo(sc.nextLong());

				System.out.println("Address : ");
				user.setAddress(Utilities.getInput());

				System.out.println("Password : ");
				user.setUserPassword(Utilities.getInput());

				System.out.println("Role Id(1 - Librarian 2 - Reader) : ");
				user.setRoleId(sc.nextInt());
			}

			if (userDAO.addUser(user)) {
				signUpStatus = true;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return signUpStatus;
	}

	public boolean subscription(Scanner sc, User u) {
		Subscription subscription = new Subscription();
		boolean status = false;
		try {
			if (userDAO.isUserExists(u.getEmailId())) {
				con = DbConnection.getCon();
				ps = con.prepareStatement(
						"insert into subscription(amount,dateOfSubscription, validity, approved)values(?,?,?,?)");
				ps.setLong(1, subscription.getAmount());
				ps.setDate(2, Date.valueOf(subscription.getDateOfSubscription()));
				ps.setDate(3, Date.valueOf(subscription.getValidity()));// Sir would apply the method in SQL.
				ps.setBoolean(4, subscription.isApproved());
			} else {
				System.out.println("Please login first!!!");
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
	
	public boolean approveBookBorrow(BookBorrow bb, int bookId,int userId, Scanner Sc) {
		ArrayList<BorrowedBookDetail> borrowedBookDetailList =bookBorrowDAO.ShowListOfBooksBorrowDetails();
		//Bookborrow requests pending for approval.
		for(BorrowedBookDetail bbd:borrowedBookDetailList)
		{
			//showAllProperties for each object.
		}
		//select bookborrow id to approve
		//bookborrow idInputread
//		bookBorrowDAO.approveBookBorrow(bookBorrowId);
		
		boolean approvalStatus= false;
		try
		{
			Subscription subscription=subscriptionDAO.ShowSubscriptionByUserId(userId);
		//to check subscription validity to allow borrow.
		if(subscription.getValidity().isAfter(LocalDate.now()))
			 {
				ps1=con.prepareStatement("update BookBorrow set borrowApproved=? where bookId=?");
				
					ps.setBoolean(1, true);
					ps.setInt(2, bookId);
					int count =ps.executeUpdate();
				
					if(count==1)
					{
						approvalStatus=true;//Have to complete the method.
					}
			 }
			 else//if subscription validity is over  
			 {
			subscriptionDAO.deleteSubscription(subscription.getSubscriptionId());
				 System.out.println("Subscription expired..Please renew to avail the facility!");
			 }
			
		
//			 	else//for rejection
//			{
//				booksBorrow.remove(bb);
//				System.out.print("Payment failed. Please try again!");
//			}
		}
		catch (Exception e) 
		{
			System.out.println(e);
			e.printStackTrace();
		}
			return approvalStatus;
	}

}