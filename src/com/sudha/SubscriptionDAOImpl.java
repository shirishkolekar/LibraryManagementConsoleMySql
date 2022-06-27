package com.sudha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class SubscriptionDAOImpl implements SubscriptionDAO{

	static Connection con;
	static PreparedStatement ps;
	static ResultSet rs;

	@Override
	public ArrayList<Subscription> ShowAllSubscriptions(Subscription subscription) {

		ArrayList<Subscription>subscriptions=new ArrayList<Subscription>();
		
		try
		{
			con = DbConnection.getCon();
			ps = con.prepareStatement("select* from Subscription");
			rs = ps.executeQuery();
			
			if(rs.next()) 
			{
				subscription.setSubscriptionId(rs.getInt("subscriptionId"));
				subscription.setUserId(rs.getInt("userId"));
				subscription.setAmount(rs.getLong("amount"));
				subscription.setDateOfSubscription(rs.getDate("dateOfSubscription").toLocalDate());
				subscription.setValidity(rs.getDate("validity").toLocalDate());
				subscription.setApproved(rs.getBoolean("approved"));
				subscriptions.add(subscription);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		return subscriptions;
	}
}

