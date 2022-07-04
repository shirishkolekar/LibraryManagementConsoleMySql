package com.sudha;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Operation {
	public static BookDAO bookDAO = new BookDAOImpl();
	public static UserDAO userDAO = new UserDAOImpl();
	public static SubscriptionDAO subscriptionDAO = new SubscriptionDAOImpl();

	ArrayList<User> users = new ArrayList<User>();
	BookBorrowDAO bookBorrowDAO = new BookBorrowDAOImpl();
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
		bookName=sc.next();
		Book book=null;
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

	public static void showMyAccount(Scanner sc, User user) {
		User u = userDAO.getUserById(user.getUserId());
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
	
	public static void changeAddress(Scanner sc, LoggedInUser l) {
		System.out.print("Enter you new Address : ");
		l.getUser().setAddress(Utilities.getInput());
		if (userDAO.editUser(l.getUser().getAddress())) {
			System.out.println("Successfully updated!");
		} else {
			System.out.println("failed");
		}
	}
	
	public static void searchUser(Scanner sc){// have to correct method
		
		System.out.println("Choose an option to search User\n"+"1 - Search by UserId : \n"+"1 - Search by Contact No. : \n"+"1 - Search by User Name : ");
		int searchInput = sc.nextInt();
		User u=null;
		userDAO.searchUser(searchInput);
		
		u=new User();
		System.out.println(u.getUserId() + " " + u.getUserName() + " " + u.getAddress() + " " + u.getContactNo() + " "
				+ u.getEmailId() + " " + u.getRegistrationDate() + " " + u.getRoleId() + " " + u.getUserPassword() + " "
				+ u.isUserStatus());
		
		else
		{
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

	public void approveBookBorrow(Scanner Sc) {
		ArrayList<BorrowedBookDetail> borrowedBookDetailList = bookBorrowDAO.ShowListOfBooksBorrowDetails();
		// Bookborrow requests pending for approval.
		for (BorrowedBookDetail bbd : borrowedBookDetailList) {
			// showAllProperties for each object.
		}
		// select bookborrow id to approve
		// bookborrow idInputread
//		bookBorrowDAO.approveBookBorrow(bookBorrowId);

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
	
	public static void approveSubscription(Scanner sc) {//need to complete it
		Subscription subscription = new Subscription();
		
		System.out.println("Subscription Ids to be approved :");
		System.out.println(subscription.getSubscriptionId());
		
		System.out.println("Do you want to approve subscription (y/n)");
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select subscriptionId where approved=false");
			rs = ps.executeQuery();

			while (rs.next()) {
				subscription.setSubscriptionId(rs.getInt("subscriptionId"));
				System.out.println(subscription.getSubscriptionId());
			}

			System.out.println("Do you want to approve subscription (y/n)");
			char input = sc.next().charAt(0);

			System.out.print("Enter the subscription Id you want to approve or reject:");
			int subscriptionId = sc.nextInt();
			if (input == 'Y' || input == 'y')// to approve subscription
			{
				ps1 = con.prepareStatement("update Subscription set approved=false, where subscriptionId=?");
				ps1.setInt(1, subscriptionId);
				ps1.setBoolean(2, false);// need to check from sir.
				int count = ps1.executeUpdate();

				if (count == 1) {
					status = true;
				}
			} else// To reject subscription
			{
				System.out.println("Payment failed..try again!");
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

		return status;
	}

}