package com.sudha;

import java.sql.Date;
import java.time.LocalDate;

public class User {
	private int userId;
	private String userStatus;
	private String userName;
	private long contactNo;
	private String emailId;
	private String address;
	private LocalDate registrationDate;
	private int roleId;
	private String userPassword;
	
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public User() {
		this.userId = 0;
		this.userStatus="";
		this.userName = "";
		this.contactNo = 0;
		this.emailId = "";
		this.address = "";
		this.registrationDate = LocalDate.now();
		this.roleId = 0;
		this.userPassword = "";
	}
	public User(int userId,String userStatus, String userName, long contactNo, String emailId, String address,
			LocalDate registrationDate, int roleId, String userPassword) {
		this.userId = userId;
		this.userStatus=userStatus;
		this.userName = userName;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.address = address;
		this.registrationDate = registrationDate;
		this.roleId = roleId;
		this.userPassword = userPassword;
	}
}
