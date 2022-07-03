package com.sudha;

import java.util.ArrayList;

public interface UserDAO {
	boolean addUser(User user);

	boolean editUser(User user);

	boolean removeUser(int userId);

	ArrayList<User> displayAllUsers(int roleId);

	User getUserById(int userId);

	boolean isUserExists(String email);

	LoggedInUser login(String email, String passwd);
}