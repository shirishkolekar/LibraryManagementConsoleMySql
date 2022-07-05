package com.sudha;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Operation implements ProjectConfig {
	public static BookDAO bookDAO = new BookDAOImpl();
	public static UserDAO userDAO = new UserDAOImpl();
	public static SubscriptionDAO subscriptionDAO = new SubscriptionDAOImpl();
	public static BookBorrowDAO bookBorrowDAO = new BookBorrowDAOImpl();
	ArrayList<User> users = new ArrayList<User>();

	static Connection con;

	static PreparedStatement ps;
	static PreparedStatement ps1;
	static ResultSet rs;

	public static LoggedInUser login() {
		LoggedInUser loggedInUser = null;
		System.out.print("Enter your UserName :");
		String userName = Utilities.getInput();
		System.out.print("Enter your password :");
		String password = Utilities.getInput();
		loggedInUser = userDAO.login(userName, password);
		return loggedInUser;
	}

	public static void showAdminMenu() {
		System.out.println("\n" + " 1 - Approve Signup\n" + "2 - Reject SignUp \n" + "0 - Quit");
	}

	public static void showLibrarianMenu() {
		System.out.println("\n" + " 1 - Add Book\n" + "2 - Show Books\n" + "3 - Edit Book\n" + "4 - Show All readers\n"
				+ "5 - Show All subscribers\n" + "6 - Edit User Details \n" + "7 - Search book\n" + "8 - Search User"
				+ "9 - Deactivate User" + "10 - Deactivate Book" + "11 - Approve subscription\n"
				+ "12 - Approve Borrow\n" + "13 - Approve return\n" + "0 -Quit \n");
	}

	public static void showReaderMenu() {
		System.out.println("\n 1- Show Books\n" + "2 - Show my Account\n" + "3 - Change address\n" + "4 - Search Book\n"
				+ "5 - subscription\n" + "6 - Borrow Book" + "7 - Return book\n" + "0 - Quit");
	}

	// Librarian functions

	public static void showBooks() {
		for (Book b : bookDAO.getAllBooks()) {
			System.out.println(b.getBookId() + " " + b.getBookName() + " " + b.getAuthor() + " " + b.getReview() + " "
					+ b.getEdition() + " " + b.getQuantity() + " " + b.getGenreId());
		}
	}

	public static void showAllUsers() {
		for (User u : userDAO.displayAllUsers(0)) {
			System.out.println(u.getUserId() + " " + u.getUserName() + " " + u.getAddress() + " " + u.getContactNo()
					+ " " + u.getEmailId() + " " + u.getRegistrationDate() + " " + u.getRoleId() + " "
					+ u.getUserPassword() + " " + u.isUserStatus());
		}
	}

	public static void addBook(Scanner sc, String bookName) {
		System.out.println("Enter the book name you want to add : ");
		bookName = sc.next();
		Book book = null;
		if (bookDAO.addBook(book)) {
			if (bookDAO.isBookAlreadyExists(bookName)) {
				System.out.print("Enter the quantity of the book to add  :");
				book.setQuantity(sc.nextInt());
				bookDAO.addBook(book);
			} else {
				book = new Book();
				System.out.print("Book Name : ");
				book.setBookName(Utilities.getInput());
				System.out.print("Author Name : ");
				book.setAuthor(Utilities.getInput());
				System.out.print("Edition : ");
				book.setEdition(sc.nextInt());
				System.out.print("Quantity : ");
				book.setQuantity(sc.nextInt());
				System.out.print("Genre : ");
				book.setGenreId(sc.nextInt());
				bookDAO.addBook(book);
			}
			System.out.println("Successfully Added");
		} else {
			System.out.println("Operation failed..Please try again!");
		}
	}

	public static void addUser(Scanner sc) {
		User user = new User();
		System.out.print("Reader's Name : ");
		user.setUserName(Utilities.getInput());
		System.out.print("Contact No : ");
		user.setContactNo(sc.nextInt());
		System.out.print("Email Id : ");
		user.setEmailId(Utilities.getInput());
		System.out.print("Address : ");
		user.setAddress(Utilities.getInput());
		if (userDAO.addUser(user)) {
			System.out.println("Successfully Added");
		} else {
			System.out.println("failed");
		}
	}

	public static void deleteBook(Scanner sc) {
		System.out.print("Enter the book Id you want to delete : ");
		int bookId = sc.nextInt();

		if (bookDAO.deleteBook(bookId)) {
			System.out.println("Successfully deleted!");
		} else {
			System.out.println("deletion failed!");
		}
	}

	public static void deactivateUser(Scanner sc) {
		System.out.print("Enter the user Id you want to delete : ");
		int readerId = sc.nextInt();

		if (userDAO.removeUser(readerId)) {
			System.out.println("Successfully deactivated!");
		} else {
			System.out.println("deactivation failed!");
		}
	}

	public static void searchBook(Scanner sc) {
		System.out.print("Enter the book Id you want to display : ");
		int bookId = sc.nextInt();
		Book b = bookDAO.getBookById(bookId);
		System.out.println(b.getBookId() + " " + b.getBookName() + " " + b.getAuthor() + " " + b.getReview() + " "
				+ b.getEdition() + " " + b.getQuantity() + " " + b.getGenreId());
	}

	public static void getUserById(Scanner sc) {
		System.out.print("Enter the User Id you want to display : ");
		int userId = sc.nextInt();
		User u = userDAO.getUserById(userId);
		System.out.println(u.getUserId() + " " + u.getUserName() + " " + u.getAddress() + " " + u.getContactNo() + " "
				+ u.getEmailId() + " " + u.getRegistrationDate() + " " + u.getRoleId() + " " + u.getUserPassword() + " "
				+ u.isUserStatus());
	}

	public static void showMyAccount(Scanner sc, int userId) {
		User u = userDAO.getUserById(userId);
		System.out.println(u.getUserId() + " " + u.getUserName() + " " + u.getAddress() + " " + u.getContactNo() + " "
				+ u.getEmailId() + " " + u.getRegistrationDate() + " " + u.getRoleId() + " " + u.getUserPassword() + " "
				+ u.isUserStatus());
	}

	public static void editBook(Scanner sc) {
		Book book = new Book();
		System.out.print("Enter the book Id you want to edit information of : ");
		book.setBookId(sc.nextInt());
		System.out.print("Book Name : ");
		book.setBookName(Utilities.getInput());
		System.out.print("Author Name : ");
		book.setAuthor(Utilities.getInput());
		System.out.print("Edition : ");
		book.setEdition(sc.nextInt());
		System.out.print("Quantity : ");
		book.setQuantity(sc.nextInt());
		System.out.print("Genre : ");
		book.setGenreId(sc.nextInt());

		if (bookDAO.editBook(book)) {
			System.out.println("Successfully updated!");
		} else {
			System.out.println("failed");
		}
	}

	public static void editUser(Scanner sc) {
		User user = new User();
		System.out.print("Enter the User Id you want to edit information of : ");
		user.setUserId(sc.nextInt());

		System.out.print("User Name : ");
		user.setUserName(Utilities.getInput());
		System.out.print("Adress : ");
		user.setAddress(Utilities.getInput());
		System.out.print("Contact No. : ");
		user.setContactNo(sc.nextLong());
		// System.out.print("User Status : ");
		// user.setUserStatus(Utilities.getInput());

		if (userDAO.editUser(user)) {
			System.out.println("Successfully updated!");
		} else {
			System.out.println("failed");
		}
	}

	public static void changeAddress(Scanner sc, int userId) {

		System.out.print("Enter you new Address : ");
		String address = Utilities.getInput();

		User u = userDAO.getUserById(userId);
		u.setAddress(address);

		if (userDAO.editUser(u)) {
			System.out.println("Successfully updated!");
		} else {
			System.out.println("failed");
		}
	}

	public static void searchUser(Scanner sc) {// have to correct method

		System.out.println("Choose an option to search User\n" + "1 - Search by UserId : \n"
				+ "1 - Search by Contact No. : \n" + "1 - Search by User Name : ");
		int searchInput = sc.nextInt();
		User u = userDAO.searchUser(searchInput);
		if (u != null) {
			System.out.println(u.getUserId() + " " + u.getUserName() + " " + u.getAddress() + " " + u.getContactNo()
					+ " " + u.getEmailId() + " " + u.getRegistrationDate() + " " + u.getRoleId() + " "
					+ u.getUserPassword() + " " + u.isUserStatus());
		} else {
			System.out.println("User does not exist!");
		}
	}

	public static boolean signUp(Scanner sc) {
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

				if (user.getRoleId() == 1) {
					user.setUserStatus(false);
				} else
					user.setUserStatus(true);
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

	public static void addSubscription(Scanner sc, int userId) {

		System.out.print("\n\n\t Enter number of days you want to take subscription for : ");
		int days = sc.nextInt();

		BigDecimal subscriptionAmount = new BigDecimal(dailySubscriptionAmount * days);
		if (subscriptionDAO.addSubscription(userId, subscriptionAmount, days)) {
			System.out.println("Subscription Added! Waiting for approval!");
		} else {
			System.out.println("Failed to add Subscription! Please Try Again!");
		}
	}

	public static void approveBookBorrow(Scanner sc,int bookBorrow, int bookId, int userId) {
		boolean approvalStatus = false;
		ArrayList<BorrowedBookDetail> borrowedBookDetailList = bookBorrowDAO.ShowListOfBooksBorrowDetails();
		// Bookborrow requests pending for approval.
		System.out.println("BookBorrowId\t"+"BookId\t"+"BookName");
		for (BorrowedBookDetail bbd : borrowedBookDetailList) {
			System.out.println(bbd.getBookBorrowId()+" "+bbd.getBookId()+bbd.getUserId()" "+bbd.getBookName()); 
		}
		System.out.print("Enter BookBorrow Id to for approval :");
		bookBorrow = sc.nextInt();

			Subscription subscription = subscriptionDAO.ShowSubscriptionByUserId(userId);
			System.out.println(subscription);
			// to check subscription validity to allow borrow.
			if (subscription.getValidity().isAfter(LocalDate.now())) {
				bookborrowDAO.approveBookBorrow(int bookBorrowId , int bookId, int userId)			} else// if subscription validity is over
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
		// return approvalStatus;
	}

	public static void approveRejectLibrarian(Scanner sc, boolean toApprove) {
		ArrayList<User> users = userDAO.displayAllUsers(2);
		if (users.size() > 0) {
			for (User l : users) {
				if (toApprove) {
					if (!l.isUserStatus()) {
						System.out.println(l.getUserId() + " " + l.getUserName() + " " + l.getAddress() + " "
								+ l.getContactNo() + " " + l.getEmailId() + " " + l.getRegistrationDate() + " "
								+ l.getRoleId() + " " + l.getUserPassword() + " " + l.isUserStatus());
					}
				} else {
					if (l.isUserStatus()) {
						System.out.println(l.getUserId() + " " + l.getUserName() + " " + l.getAddress() + " "
								+ l.getContactNo() + " " + l.getEmailId() + " " + l.getRegistrationDate() + " "
								+ l.getRoleId() + " " + l.getUserPassword() + " " + l.isUserStatus());
					}
				}
			}
			if (toApprove) {
				System.out.println("Enter Librarian Id to approve!");
			} else {
				System.out.println("Enter Librarian Id to reject!");
			}
			int librarianId = sc.nextInt();
			boolean operationStatus = false;
			if (toApprove) {

				operationStatus = userDAO.approveRejectLibrarian(librarianId, true);
			} else {
				operationStatus = userDAO.approveRejectLibrarian(librarianId, false);
			}

			if (operationStatus) {
				System.out.println("Librarian " + (toApprove ? "approved" : "rejected") + " successfully!");
			} else {
				System.out.println("Operation failed!");
			}
		} else {
			System.out.println("No Librarian is pending for approval/rejection!");
		}
	}

	public static void approveSubscription(Scanner sc) {

//		 show list of subscriptions to be approved
		ArrayList<Subscription> subscriptionsToApprove = subscriptionDAO.ShowAllSubscriptionsToBeApproved();

		for (Subscription s : subscriptionsToApprove) {
			System.out.println(s.getSubscriptionId());
		}

//		 ask librarian for subscription id to approve 

		System.out.print("Enter the subscription Id you want to approve or reject:");
		int subscriptionId = sc.nextInt();

//		 ask whether to approve or reject
		System.out.print("Enter 1 for approve or 2 for reject : ");
		int decision = sc.nextInt();

//		 save the change in database
//		 	- if approve set flag to true
//		 	- if reject then delete entry in db

		if (subscriptionDAO.approveOrRejectSubscription(subscriptionId, decision)) {
			// db operation is successful
			if (decision == 1) {
				System.out.print("\n\n\t Subscription Approved Successfully!");
			} else {
				System.out.print("\n\n\t Subscription rejected Successfully!");
			}
		} else {
			// db operation is failed
			System.out.print("\n\n\t Operation on Subscription failed! Please try again!");
		}
	}

	public static void borrowBookRequest(int userId) {
		if (subscriptionDAO.isUserSubscribed(userId)) {
			bookBorrowDAO.borrowBook(userId);

		} else {
			System.out.println("Please subscribe first to avail the services!");
		}
		if (bookBorrowDAO.borrowBook(userId)) {
			System.out.println("Request sent for approval for book borrowed!");
		} else {
			System.out.println("Operation failed!");
		}
	}

	public static void review(Scanner sc, String bookName, int review) {
		System.out.print("Enter the book name you want to review : ");
		bookName = Utilities.getInput();

		System.out.print("Enter your review :");
		review = sc.nextInt();

		if (bookDAO.review(bookName, review)) {
			System.out.println("Thanks to review the book!");
		} else {
			System.out.println("Operation failed..try again!");
		}
	}
	public static void returnBook(Scanner sc,int bookBorrowId)
	{
		System.out.print("Enter the BookBorrow Id you want to return : ");
		bookBorrowId=sc.nextInt();
		if(bookBorrowDAO.returnBook(bookBorrowId))
		{
			System.out.print("Request sent for approval! ");
		}
		else
		{
			System.out.print("Operation Failed!");
		}
	}
	
	public static void approveReturn(Scanner sc, int bookBorrowId)
	{
		System.out.println(" List of the books for return approval ");
		bookBorrowDAO.listOfBooksToBeReturned(null);
		
		System.out.print("Enter bookBorrowId you want to approve return of :");
		bookBorrowId = sc.nextInt();
		
		if(bookBorrowDAO.approveReturn(bookBorrowId) 
		{
			System.out.print("Return Approved! ");
		}
		else
		{
			System.out.print("Return Failed!");
		}
	}
}