package com.sudha;

import java.util.Scanner;

import com.sudha.Utilities.LoginStatus;

public class LibraryManager {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
		System.out.print("Welcome to Lirbrary!" );
		System.out.print("Please login to proceed!" );
		
		LoggedInUser loggedInUser = Operation.login();
		
		if(loggedInUser != null && loggedInUser.getLoginStatus() == LoginStatus.Success)
		{
			System.out.print("\n\n\t Login successfull!");
		
			//switching based on role
			switch(loggedInUser.getUser().getRoleId())
			{
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
		}
		else
		{
			// extra work to be doen if login failed
		}
		
		
		
		
//		System.out.println("1- Admin Login  2- Reader Login");
//		int input=sc.nextInt();
//		menuloop1:	
//		if(input==1)
//			{
//				do
//				{
//					System.out.print("Enter your UserId :" );
//					String adminUserIdInput=sc.next();
//					System.out.print("Enter your password :" );
//					int adminPassInput=sc.nextInt();
//			
//					if(adminUserIdInput.equals(adminUserId)&& adminPassInput==adminPass)
//					{
//						isCorrectLogin=true;
//						Operation.loginAdmin();				
//						int librairanOperation = sc.nextInt();
//
//						switch (librairanOperation)
//						{
//						case 1: // Display Books
//							Operation.showBooks();
//						break;
//
//						case 2:// Add Book
//							Operation.addBook(sc);
//						break;
//
//						case 3:// delete Book
//							Operation.deleteBook(sc);
//						break;
//
//						case 4:// Get Book details by BookId
//							Operation.getBookById(sc);
//						break;
//
//						case 5:// Edit Book
//							Operation.editBook(sc);
//						break;
//						
//						case 6: // Display Users
//							Operation.showAllUsers();
//						break;
//
//						case 7:// Add User
//							Operation.addUser(sc);
//						break;
//
//						case 8:// Deactivate User
//							Operation.deactivateUser(sc);
//						break;
//
//						case 9:// display user details by userId
//							Operation.getUserById(sc);
//						break;
//
//						case 10:// Edit Book
//							Operation.editUser(sc);
//						break;
//
//						default: 
//							System.out.println("Invalid input!");
//						break;
//
//					}// Switch case closed
//				 }
//				else
//				{
//					isCorrectLogin=false;
//				}
//			}
//			while(isCorrectLogin=false);		
//					
//				System.out.println("Do you want more operations? (y/n):");
//				char moreOps=sc.next().charAt(0);
//				if(moreOps=='y'|| moreOps=='Y')
//				{
//					Operation.loginAdmin();	
//				}
//				else
//				{
//					break menuloop1;
//				}
//					
//			}//admin closed
//			 
//		else//reader login
//		{
//			System.out.print("1 - Login  2- sign up/n"+ "Choose one option : ");
//		}
		sc.close();
	}
}
