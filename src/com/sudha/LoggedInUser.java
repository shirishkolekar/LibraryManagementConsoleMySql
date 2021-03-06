package com.sudha;

import com.sudha.Utilities.LoginStatus;

public class LoggedInUser {
	private User user;
	private LoginStatus loginStatus;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoginStatus getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(LoginStatus loginStatus) {
		this.loginStatus = loginStatus;
	}

	public LoggedInUser() {
		this.user = null;
		this.loginStatus = null;
	}

	public LoggedInUser(User user, LoginStatus loginStatus) {
		this.user = user;
		this.loginStatus = loginStatus;
	}

}