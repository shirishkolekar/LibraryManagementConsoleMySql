package com.sudha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.sudha.Library.LoginStatus;

public class Operation {
	UserDAO userDAO = new UserDAOImpl();

	ArrayList<User> users = new ArrayList<User>();

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
				if(userDAO.isUserExists(user.getEmailId()))
					{
						System.out.println("Email already exists..try again!");
					}
				else 
				{	
					System.out.println("User's Name : ");
					user.setReaderName(Utilities.getInput());

					System.out.println("Contact Number : ");
					user.setContactNo(sc.nextLong());

					System.out.println("Address : ");
					user.setAddress(Utilities.getInput());

					System.out.println("Password : ");
					user.setReaderPassword(Utilities.getInput());

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
}