package com.sudha;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public interface SubscriptionDAO {
	ArrayList<Subscription> ShowAllSubscriptions();

	Subscription ShowSubscriptionByUserId(int userId);

	boolean approveSubscription(Scanner sc);

	boolean isUserSubscribed(int userId);

	boolean deleteSubscription(int subscriptionId);

	ArrayList<Subscription> ShowAllSubscriptionsToBeApproved();

	boolean approveOrRejectSubscription(int subscriptionId, int approveOrReject);

	boolean addSubscription(int userId, BigDecimal amount, int days);
}