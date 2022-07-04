package com.sudha;

import java.util.ArrayList;

public interface UserDAO {
	boolean addUser(User user);
	User getUserById(int userId);
	boolean editUser(User user);

	boolean removeUser(int userId);

	ArrayList<User> displayAllUsers(int roleId);

	public User searchUser(int searchInput);
	boolean isUserExists(String email);

	LoggedInUser login(String email, String passwd);
	boolean approveRejectLibrarian(int librarianId, boolean isApproved);
	boolean editUser(String address);

}