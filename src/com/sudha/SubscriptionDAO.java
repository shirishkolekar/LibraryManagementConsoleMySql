package com.sudha;

import java.util.ArrayList;
import java.util.Scanner;

public interface SubscriptionDAO {
	ArrayList<Subscription> ShowAllSubscriptions();
	Subscription ShowSubscriptionByUserId(int userId);
	boolean approveSubscription(Scanner sc);
	boolean isUserSubscribed(int userId);
	boolean deleteSubscription(int subscriptionId);

}
