package com.sudha;

import java.util.ArrayList;
import java.util.Scanner;

import com.sudha.Utilities.LoginStatus;

public class LibraryManager {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) 
	{
		BookDAO bookDAO=new BookDAOImpl();
		SubscriptionDAO subscriptionDAO = new SubscriptionDAOImpl();
		String bookName = null;
		int selectedOption = -1;
		boolean loginAgain = false;
		boolean signUpStatus = false;
		System.out.print("Welcome to Lirbrary!");
		do {
			// login again - start

			do {
				System.out.print("\n\n\t Are you an existing user or new user? 1 - New User 2 - Existing User");
				System.out.print("\n\n\t Your Choice : ");
				if (sc.nextInt() == 1) {
					signUpStatus = Operation.signUp(sc);
				} else if (sc.nextInt() == 2) {
					signUpStatus = true;
				}
			} while (!signUpStatus);

			System.out.print("Please login to proceed!");

			LoggedInUser loggedInUser = Operation.login();

			if (loggedInUser != null && loggedInUser.getLoginStatus() == LoginStatus.Success) {
				System.out.print("\n\n\t Login successfull!");
				do {
					// switching based on role
					switch (loggedInUser.getUser().getRoleId()) {
					case 1:
						Operation.showAdminMenu();
						break;
					case 2:
						Operation.showLibrarianMenu();
						break;
					case 3:
						Operation.showReaderMenu();
						break;
					}
					System.out.print("\n\n\t Your Choice!");
					selectedOption = sc.nextInt();

					switch (loggedInUser.getUser().getRoleId()) {
					case 1:
						// admin function calls - start
						switch (selectedOption) {
						case 1: // approve Librarian
							Operation.approveRejectLibrarian(sc, true);
							break;
						case 2:// reject Librarian
							Operation.approveRejectLibrarian(sc, false);
							break;
						case 3:// quit
							loggedInUser = null;
							break;
						default:
							System.out.print("\n\n\t Invalid Option selected!");
							break;
						}
						// admin function calls - end
						break;
					case 2:
						// librarian function calls - start
						switch (selectedOption) {
						case 1: // Add Book
							Operation.addBook(sc,bookName );
							break;
						case 2:// Show all books
							bookDAO.getAllBooks();
							break;
						case 3:// Edit book
							Operation.editBook(sc);
							break;
						case 4:// Show all users
							Operation.showAllUsers();
							break;
						case 5:// Show all Subscribers
							subscriptionDAO.ShowAllSubscriptions();
							break;
						case 6:// Edit User Details
							Operation.editUser(sc);
							break;
						case 7:// Search book
							Operation.searchBook(sc);
							break;
						case 8:// Search user
							Operation.searchUser(sc);
							break;
						case 9:// Deactivate User
							Operation.deactivateUser(sc);
							break;
						case 10:// Deactivate Book
							Operation.deleteBook(sc);
							break;
						case 11:// Approve subscription
							Operation.approveSubscription(sc);
							break;
						case 12:// Approve Borrow
							Operation.approveBookBorrow(sc);
							break;
						case 13:// Approve Return
							Operation.approveReturn(sc);
							break;
						
						default:
							System.out.print("\n\n\t Invalid Option selected!");
							break;
						}
						// librarian function calls - end

						break;
					case 3:
						// reader function calls - start
						switch (selectedOption) {
						case 1: // Show Books
							Operation.showBooks();
							break;
						case 2://Show my account							
							Operation.showMyAccount(sc,loggedInUser.getUser().getUserId());
							break;
						case 3:// Change Address
							Operation.changeAddress(sc, loggedInUser.getUser().getUserId());
							break;
						case 4:// Search book
							Operation.searchBook(sc);
							break;
						case 5:// Subscription
							Operation.addSubscription(sc,loggedInUser.getUser().getUserId());//Need to understand from sir.
							break;
						case 6:// Borrow book
							Operation.borrowBookRequest(sc,loggedInUser.getUser().getUserId());						
							break;
						case 7:// Return Book//
							Operation.returnBook(sc,loggedInUser.getUser().getUserId());
							break;
						case 8:// Review book//no method
							//Operation.review(sc,bookName,review);// need to check
							break;
						default:
							System.out.print("\n\n\t Invalid Option selected!");
							break;
						}
						// reader function calls - end
						break;
					}

				} while (selectedOption != 0);

			} else {
				if (loggedInUser != null && loggedInUser.getLoginStatus() == LoginStatus.PasswordIncorrect) {
					//
				} else if (loggedInUser != null && loggedInUser.getLoginStatus() == LoginStatus.UserNotFound) {
					// register
				}
			}

		} while (loginAgain);
		// login again - end
		System.out.println("\n\n\t Thank you for using Library Manager!");

		sc.close();
	}
}