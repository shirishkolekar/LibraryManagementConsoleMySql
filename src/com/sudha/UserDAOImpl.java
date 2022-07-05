package com.sudha;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
					"insert into table User(UserName,address,contactNo,emailId,regDate,roleId,userPassword,userStatus)values(?,?,?,?,?,?,?)");
			ps.setString(1, r.getUserName());
			ps.setString(2, r.getAddress());
			ps.setLong(3, r.getContactNo());
			ps.setString(4, r.getEmailId());
			ps.setDate(5, Date.valueOf(r.getRegistrationDate()));
			ps.setInt(6, r.getRoleId());
			ps.setString(7, r.getUserPassword());
			ps.setBoolean(8, r.isUserStatus());

			int count = ps.executeUpdate();
			if (count == 1) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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

			ps.setBoolean(1, user.isUserStatus());
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
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public boolean removeUser(int userId) {
		boolean status = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("update user set userStatus=0 where userId=?");

			ps.setInt(userId, userId);

			int count = ps.executeUpdate();

			if (count == 1) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public ArrayList<User> displayAllUsers(int roleId) {
		ArrayList<User> users = new ArrayList<User>();
		User u = null;
		try {
			con = DbConnection.getCon();
			String query = "select * from users";
			if (roleId != 0) {
				query += "where roleId = ?";
			}
			ps = con.prepareStatement(query);
			if (roleId != 0) {
				ps.setInt(0, roleId);
			}
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
				u.setUserStatus(rs.getBoolean("userStatus"));
				users.add(u);
				con.close();
			}
		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}

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
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return u;
	}

// correction needed
	@Override
	public User searchUser(int searchInput) {
		User u = null;
		try {
			con = DbConnection.getCon();
			if (searchInput == 1) {
				ps = con.prepareStatement("select * from users where userId=?");
				ps.setInt(1, u.getUserId());
			} else if (searchInput == 2) {
				ps = con.prepareStatement("select * from users where contactNo=?");
				ps.setLong(1, u.getContactNo());
			} else if (searchInput == 3) {
				ps = con.prepareStatement("select * from users where userName=?");
				ps.setString(1, u.getUserName());
			}
			rs = ps.executeQuery();

			if (rs.next()) {
				u = new User();
				u.setUserId(rs.getInt("userId"));
				u.setUserName(rs.getString("readerName"));
				u.setContactNo(rs.getLong("contactNo"));
				u.setEmailId(rs.getString("emailId"));
				u.setAddress(rs.getString("address"));
				u.setRegistrationDate(rs.getDate("registrationDate").toLocalDate());
				u.setRoleId(rs.getInt("roleId"));
				u.setUserStatus(rs.getBoolean("userStatus"));
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exists;
	}

	@Override
	public LoggedInUser login(String email, String passwd) {

		LoggedInUser loggedInUser = new LoggedInUser();
		User u = null;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select * from users where emailId=?");
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				u = new User();
				u.setUserId(rs.getInt("userId"));
				u.setUserName(rs.getString("readerName"));
				u.setContactNo(rs.getLong("contactNo"));
				u.setEmailId(rs.getString("emailId"));
				u.setAddress(rs.getString("address"));
				u.setRegistrationDate(rs.getDate("registrationDate").toLocalDate());
				u.setRoleId(rs.getInt("roleId"));
				u.setUserStatus(rs.getBoolean("userStatus"));
				loggedInUser.setUser(u);
				if (rs.getString("userPassword").equals(passwd)) {
					loggedInUser.setLoginStatus(LoginStatus.Success);
				} else {
					loggedInUser.setLoginStatus(LoginStatus.PasswordIncorrect);
				}
			} else {
				loggedInUser.setLoginStatus(LoginStatus.UserNotFound);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loggedInUser;
	}

	@Override
	public boolean approveRejectLibrarian(int librarianId, boolean isApproved) {
		boolean status = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("update users set userStatus=? where userId = ?");
			ps.setBoolean(1, isApproved);
			ps.setInt(2, librarianId);
			int count = ps.executeUpdate();
			if (count == 1) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public boolean editUser(String address) {
		// TODO Auto-generated method stub
		return false;
	}
}
