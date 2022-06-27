package com.sudha;

import java.util.ArrayList;
import java.util.Scanner;

public interface SubscriptionDAO {
	ArrayList<Subscription> ShowAllSubscriptions(Subscription subscription);
	public boolean approveSubscription(Scanner sc);

}
