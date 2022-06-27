package com.sudha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class SubscriptionDAOImpl implements SubscriptionDAO {

	static Connection con;
	static PreparedStatement ps;
	static PreparedStatement ps1;
	static ResultSet rs;

	@Override
	public ArrayList<Subscription> ShowAllSubscriptions(Subscription subscription) {

		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();

		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select* from Subscription");
			rs = ps.executeQuery();

			if (rs.next()) {
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
		}
		return subscriptions;
	}

	@Override
	public boolean approveSubscription(Scanner sc) {
		boolean status = false;

		Subscription subscription =new Subscription();
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
			int subscriptionId=sc.nextInt();
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
		}

		return status;
	}
}
