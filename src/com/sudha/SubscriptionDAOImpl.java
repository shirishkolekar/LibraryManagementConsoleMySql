package com.sudha;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class SubscriptionDAOImpl implements SubscriptionDAO, ProjectConfig {

	static Connection con;
	static PreparedStatement ps;
	static PreparedStatement ps1;
	static ResultSet rs;

	@Override
	public ArrayList<Subscription> ShowAllSubscriptions() {
		Subscription subscription = null;
		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();

		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select* from Subscription");
			rs = ps.executeQuery();

			while (rs.next()) {
				subscription = new Subscription();
				subscription.setSubscriptionId(rs.getInt("subscriptionId"));
				subscription.setUserId(rs.getInt("userId"));
				subscription.setAmount(rs.getLong("amount"));
				subscription.setDateOfSubscription(rs.getDate("dateOfSubscription").toLocalDate());
				subscription.setValidity(rs.getDate("validity").toLocalDate());
				subscription.setApproved(rs.getBoolean("approved"));
				subscriptions.add(subscription);
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
		return subscriptions;
	}

	@Override
	public boolean approveSubscription(Scanner sc) {
		boolean status = false;

		Subscription subscription = new Subscription();
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select subscriptionId where approved=false");
			rs = ps.executeQuery();

			while (rs.next()) {
				subscription.setSubscriptionId(rs.getInt("subscriptionId"));
				System.out.println(subscription.getSubscriptionId());
			}

			System.out.println("Do you want to approve subscription (y/n)");
			char input = sc.next().charAt(0);

			System.out.print("Enter the subscription Id you want to approve or reject:");
			int subscriptionId = sc.nextInt();
			if (input == 'Y' || input == 'y')// to approve subscription
			{
				ps1 = con.prepareStatement("update Subscription set approved=false, where subscriptionId=?");
				ps1.setInt(1, subscriptionId);
				ps1.setBoolean(2, false);// need to check from sir.
				int count = ps1.executeUpdate();

				if (count == 1) {
					status = true;
				}
			} else// To reject subscription
			{
				System.out.println("Payment failed..try again!");
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
	public Subscription ShowSubscriptionByUserId(int userId) {
		Subscription subscription = null;// In which case we call constructor?
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select* from Subscription where userId =?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			if (rs.next()) {
				subscription = new Subscription();
				subscription.setSubscriptionId(rs.getInt("subscriptionId"));
				subscription.setUserId(rs.getInt("userId"));
				subscription.setAmount(rs.getLong("amount"));
				subscription.setDateOfSubscription(rs.getDate("dateOfSubscription").toLocalDate());
				subscription.setValidity(rs.getDate("validity").toLocalDate());
				subscription.setApproved(rs.getBoolean("approved"));
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
		return subscription;
	}

	@Override
	public boolean isUserSubscribed(int userId) {
		boolean exists = false;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select 1 from Subscription where userId=?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			if (rs.next()) {
				exists = true;
			}
			con.close();
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
	public boolean deleteSubscription(int subscriptionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Subscription> ShowAllSubscriptionsToBeApproved() {
		Subscription subscription = null;
		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();

		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select * from subscription where approved = 0");
			rs = ps.executeQuery();

			while (rs.next()) {
				subscription = new Subscription();
				subscription.setSubscriptionId(rs.getInt("subscriptionId"));
				subscription.setUserId(rs.getInt("userId"));
				subscription.setAmount(rs.getLong("amount"));
				subscription.setDateOfSubscription(rs.getDate("dateOfSubscription").toLocalDate());
				subscription.setValidity(rs.getDate("validity").toLocalDate());
				subscription.setApproved(rs.getBoolean("approved"));
				subscriptions.add(subscription);
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
		return subscriptions;
	}

	@Override
	public boolean approveOrRejectSubscription(int subscriptionId, int approveOrReject) {
		boolean status = false;
		try {
			con = DbConnection.getCon();
			// approveOrReject = 1 then approve and 2 then reject
			if (approveOrReject == 1) {
				ps = con.prepareStatement("update subscription set approved=0 where subscriptionId=?");
			} else {
				ps = con.prepareStatement("delete from Subscription where subscriptionId=?");
			}
			ps1.setInt(1, subscriptionId);
			int count = ps1.executeUpdate();
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
	public boolean addSubscription(int userId, BigDecimal amount, int days) {
		boolean status = false;
		con = DbConnection.getCon();
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement(
					"insert into Subscription(userId, amount, dateOfSubscription, validity, approved) values(?,?,?,?,?)");
			ps.setInt(1, userId);
			ps.setBigDecimal(2, amount);
			ps.setDate(3, Date.valueOf(LocalDate.now()));
			ps.setDate(4, Date.valueOf(LocalDate.now().plusDays(days)));
			ps.setBoolean(5, false);
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
}