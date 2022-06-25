package com.sudha;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	static BookDAO bookDAO = new BookDAOImpl();
	static UserDAO userDAO = new UserDAOImpl();
	
	static void showBooks() {
		for (Book b : bookDAO.getAllBooks()) {
			System.out.println(b.getBookId() + " " + b.getBookName() + " " + b.getAuthor() + " " + b.getReview() + " "
					+ b.getEdition() + " " + b.getQuantity() + " " + b.getGenre());
		}
	}

	static void showAllUsers() {
		for (User u : userDAO.displayAllUsers()) {
			System.out.println(u.getReaderId() + " " + u.getReaderName() + " " + u.getAddress() + " " + u.getContactNo() + " "
					+ u.getEmailId() + " " + u.getRegistrationDate() + " " + u.getRoleId()+" "+ u.getReaderPassword() + " "+u.getUserStatus());
		}
	}

	static void addBook(Scanner sc) {
		Book book = new Book();
		System.out.print("Book Name : ");
		book.setBookName(getInput());
		System.out.print("Author Name : ");
		book.setAuthor(getInput());
		System.out.print("Edition : ");
		book.setEdition(sc.nextInt());
		System.out.print("Quantity : ");
		book.setQuantity(sc.nextInt());
		System.out.print("Genre : ");
		book.setGenre(getInput());

		if (bookDAO.addBook(book)) {
			System.out.println("Successfully Added");
		} else {
			System.out.println("failed");
		}
	}

	static void addUser(Scanner sc) {
		User user = new User();
		System.out.print("Reader's Name : ");
		user.setReaderName(getInput());
		System.out.print("Contact No : ");
		user.setContactNo(sc.nextInt());
		System.out.print("Email Id : ");
		user.setEmailId(getInput());
		System.out.print("Address : ");
		user.setAddress(getInput());
		System.out.print("Status : ");
		user.setUserStatus(getInput());
		System.out.print("Registration Date : ");
		user.setRegistrationDate(sc.nextInt());
		
		if (userDAO.addUser(user)) {
			System.out.println("Successfully Added");
		} else {
			System.out.println("failed");
		}
	}

	static void deleteBook(Scanner sc)
	{
		System.out.print("Enter the book Id you want to delete : ");
		int bookId=sc.nextInt();
		
		if(bookDAO.deleteBook(bookId))
		{
			System.out.println("Successfully deleted!");
		}
		else
		{
			System.out.println("deletion failed!");
		}
	}
	
	static void deactivateUser(Scanner sc)
	{
		System.out.print("Enter the user Id you want to delete : ");
		int readerId=sc.nextInt();
		
		if(userDAO.removeUser(readerId))
		{
			System.out.println("Successfully deactivated!");
		}
		else
		{
			System.out.println("deactivation failed!");
		}
	}
	static void getBookById(Scanner sc)
	{
		System.out.print("Enter the book Id you want to display : ");
		int bookId=sc.nextInt();
		Book b=bookDAO.getBookById(bookId);
		System.out.println(b.getBookId() + " " + b.getBookName() + " " + b.getAuthor() + " " + b.getReview() + " "
				+ b.getEdition() + " " + b.getQuantity() + " " + b.getGenre());
	}

	static void getUserById(Scanner sc)
	{
		System.out.print("Enter the User Id you want to display : ");
		int readerId=sc.nextInt();
		User u=userDAO.getUserById( userId);
		System.out.println(u.getReaderId() + " " + u.getReaderName() + " " + u.getAddress() + " " + u.getContactNo() + " "
				+ u.getEmailId() + " " + u.getRegistrationDate() + " " + u.getRoleId()+" "+ u.getReaderPassword() + " "+u.getUserStatus());
	}
	static void editBook(Scanner sc)
	{
		Book book = new Book();
		System.out.print("Enter the book Id you want to edit information of : ");
		book.setBookId(sc.nextInt());
		
		System.out.print("Book Name : ");
		book.setBookName(getInput());
		System.out.print("Author Name : ");
		book.setAuthor(getInput());
		System.out.print("Edition : ");
		book.setEdition(sc.nextInt());
		System.out.print("Quantity : ");
		book.setQuantity(sc.nextInt());
		System.out.print("Genre : ");
		book.setGenre(getInput());

		if (bookDAO.editBook(book)) {
			System.out.println("Successfully updated!");
		} else {
			System.out.println("failed");
		}
	}
	static void editUser(Scanner sc)
	{
		User user = new User();
		System.out.print("Enter the User Id you want to edit information of : ");
		user.setReaderId(sc.nextInt());
		
		System.out.print("User Name : ");
		user.setReaderName(getInput());
		System.out.print("Adress : ");
		user.setAddress(getInput());
		System.out.print("Contact No. : ");
		user.setContactNo(sc.nextLong());
		System.out.print("Registraion Date : ");
		user.setRegistraionDate();
		System.out.print("User Status : ");
		user.setUserStatus(getInput());

		if (userDAO.editUser(user)) {
			System.out.println("Successfully updated!");
		} else {
			System.out.println("failed");
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("\n" + " 1 - Add Book\n" + "2 - Show Books\n" + "3 - Show Readers' Detail\n" + "4 - Search\n"
				+ "5 - Approve" + "6 - Edit Book");
		int librairanOperation = sc.nextInt();

		switch (librairanOperation) {
		case 1: // Display Books
			showBooks();
			break;

		case 2:// Add Book
			addBook(sc);
			break;

		case 3:// delete Book
			deleteBook(sc);
			break;

		case 4:// Get Book details by BookId
			getBookById(sc);
			break;

		case 5:// Edit Book
			editBook(sc);
			break;
			
		case 6: // Display Users
			showAllUsers();
			break;

		case 7:// Add User
			addUser(sc);
			break;

		case 8:// Deactivate User
			deactivateUser(sc);
			break;

		case 9:// display user details by userId
			getUserById(sc);
			break;

		case 10:// Edit Book
			editUser(sc);
			break;
			

		default: 
			
			System.out.println("Invalid input!");

			break;

		}// Switch case closed
sc.close();
	}
}