package com.sudha;

import java.util.Scanner;

import com.sudha.Utilities.LoginStatus;

public class LibraryManager {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int selectedOption = -1;
		boolean loginAgain = false;
		System.out.print("Welcome to Lirbrary!");
		do {
			// login again - start

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
						case 1: // approve Librarian
							Operation.showAdminMenu();
							break;
						case 2:// reject Librarian
							Operation.showLibrarianMenu();
							break;
						case 3:// quit
							Operation.showReaderMenu();
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
						case 1: // approve Librarian
							Operation.showAdminMenu();
							break;
						case 2:// reject Librarian
							Operation.showLibrarianMenu();
							break;
						case 3:// quit
							Operation.showReaderMenu();
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

//System.out.println("1- Admin Login  2- Reader Login");
//int input=sc.nextInt();
//menuloop1:	
//if(input==1)
//	{
//		do
//		{
//		
//	
//			if(adminUserIdInput.equals(adminUserId)&& adminPassInput==adminPass)
//			{
//				isCorrectLogin=true;
//				Operation.loginAdmin();				
//				int librairanOperation = sc.nextInt();
//
//				switch (librairanOperation)
//				{
//				case 1: // Display Books
//					Operation.showBooks();
//				break;
//
//				case 2:// Add Book
//					Operation.addBook(sc);
//				break;
//
//				case 3:// delete Book
//					Operation.deleteBook(sc);
//				break;
//
//				case 4:// Get Book details by BookId
//					Operation.getBookById(sc);
//				break;
//
//				case 5:// Edit Book
//					Operation.editBook(sc);
//				break;
//				
//				case 6: // Display Users
//					Operation.showAllUsers();
//				break;
//
//				case 7:// Add User
//					Operation.addUser(sc);
//				break;
//
//				case 8:// Deactivate User
//					Operation.deactivateUser(sc);
//				break;
//
//				case 9:// display user details by userId
//					Operation.getUserById(sc);
//				break;
//
//				case 10:// Edit Book
//					Operation.editUser(sc);
//				break;
//
//				default: 
//					System.out.println("Invalid input!");
//				break;
//
//			}// Switch case closed
//		 }
//		else
//		{
//			isCorrectLogin=false;
//		}
//	}
//	while(isCorrectLogin=false);		
//			
//		System.out.println("Do you want more operations? (y/n):");
//		char moreOps=sc.next().charAt(0);
//		if(moreOps=='y'|| moreOps=='Y')
//		{
//			Operation.loginAdmin();	
//		}
//		else
//		{
//			break menuloop1;
//		}
//			
//	}//admin closed
//	 
//else//reader login
//{
//	System.out.print("1 - Login  2- sign up/n"+ "Choose one option : ");
//}
