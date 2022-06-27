package com.sudha;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import com.sudha.Utilities.LoginStatus;

public class UserDAOImpl implements UserDAO {
	ArrayList<User> users = new ArrayList<User>();

	static Connection con;
	static PreparedStatement ps;
	static ResultSet rs;

	@Override
	public boolean addUser(User r) {

		boolean status = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement(
					"insert into table User(UserName,address,contactNo,emailId,regDate,roleId,userPassword)values(?,?,?,?,?,?,?)");

			// ps.setInt(1, ps.getInt());
			ps.setString(2, r.getUserStatus());
			ps.setString(4, r.getUserName());
			ps.setLong(5, r.getContactNo());
			ps.setString(6, r.getEmailId());
			ps.setString(7, r.getAddress());
			ps.setInt(8, r.getRoleId());
			ps.setString(9, r.getUserPassword());
			int count = ps.executeUpdate();
			if (count == 1) {
				status = true;
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean editUser(User user) {
		boolean status = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement(
					"update User set getReaderName = ?,userStatus=?,contactNo=?,emailId=?,address=?,registrationDate=?,readerPassword=? where userId = ?");

			ps.setString(1, user.getUserStatus());
			ps.setDate(2, Date.valueOf(user.getRegistrationDate()));
			ps.setString(3, user.getUserName());
			ps.setLong(4, user.getContactNo());
			ps.setString(5, user.getEmailId());
			ps.setString(6, user.getAddress());
			ps.setInt(7, user.getRoleId());
			ps.setString(8, user.getUserPassword());

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

	@Override
	public boolean removeUser(int userId) {
		boolean status = false;
		User u = new User();
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("update user set userStatus='deactived' where userId=?");

			ps.setString(userId, u.getUserStatus());

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

	@Override
	public ArrayList<User> displayAllUsers() {
		ArrayList<User> users = new ArrayList<User>();
		User u = null;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select * from users");
			rs = ps.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setUserId(rs.getInt("userId"));
				u.setUserName(rs.getString("readerName"));
				u.setContactNo(rs.getLong("contactNo"));
				u.setEmailId(rs.getString("emailId"));
				u.setAddress(rs.getString("address"));
				u.setRegistrationDate(rs.getDate("registrationDate").toLocalDate());
				u.setRoleId(rs.getInt("roleId"));
				u.setUserStatus(rs.getString("userStatus"));
				users.add(u);
				con.close();
			}
		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUserById(int userId) {
		User u = new User();
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select * from users where usesrId=?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			if (rs.next()) {
				u.setUserId(rs.getInt("userId"));
				u.setUserName(rs.getString("readerName"));
				u.setContactNo(rs.getLong("contactNo"));
				u.setEmailId(rs.getString("emailId"));
				u.setAddress(rs.getString("address"));
				u.setRegistrationDate(rs.getDate("registrationDate").toLocalDate());
				u.setRoleId(rs.getInt("roleId"));
				u.setUserStatus(rs.getString("userStatus"));
				con.close();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public boolean isUserExists(String email) {
		boolean exists = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select emailId from users where emailId=?");
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				exists = true;
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return exists;
	}

	@Override
	public LoggedInUser login(String rEmail, String rPasswd) {

		LoggedInUser loggedInUser = new LoggedInUser();
		User u = new User();
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select * from users where emailId=? and userPassword=?");
			ps.setString(1, rEmail);
			ps.setString(2, rPasswd);
			rs = ps.executeQuery();

			if (rs.next()) {
				u.setUserId(rs.getInt("userId"));
				u.setUserName(rs.getString("readerName"));
				u.setContactNo(rs.getLong("contactNo"));
				u.setEmailId(rs.getString("emailId"));
				u.setAddress(rs.getString("address"));
				u.setRegistrationDate(rs.getDate("registrationDate").toLocalDate());
				u.setRoleId(rs.getInt("roleId"));
				u.setUserStatus(rs.getString("userStatus"));
				loggedInUser.setUser(u); //
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

		if (loggedInUser.getUser() != null) {
			if (loggedInUser.getUser().getUserPassword().equals(rPasswd)) {
				loggedInUser.setLoginStatus(LoginStatus.Success);
			} else {
				loggedInUser.setLoginStatus(LoginStatus.PasswordIncorrect);
			}
		} else {
			loggedInUser.setLoginStatus(LoginStatus.UserNotFound);
		}

		return loggedInUser;
	}

	@Override
	public boolean isUserSubscribed(String email)
	{
		boolean exists = false;
		try 
		{
			con = DbConnection.getCon();
			ps = con.prepareStatement("select emailId from Subscription where emailId=?");
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				exists = true;
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return exists;
	}

