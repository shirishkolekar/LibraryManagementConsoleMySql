package com.sudha;

import java.time.LocalDate;

public class Subscription {

	private int subscriptionId;
	private int userId;
	private long amount;
	private LocalDate dateOfSubscription;
	private LocalDate validity;
	private boolean approved;

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public LocalDate getDateOfSubscription() {
		return dateOfSubscription;
	}

	public void setDateOfSubscription(LocalDate dateOfSubscription) {
		this.dateOfSubscription = dateOfSubscription;
	}

	public LocalDate getValidity() {
		return validity;
	}

	public void setValidity(LocalDate validity) {
		this.validity = validity;
	}

	public Subscription() {
		this.userId = 0;
		this.amount = 1000;
		this.dateOfSubscription = null;
		this.validity = null;
		this.approved = false;
		subscriptionId = 0;
	}

	public Subscription(int subscriptionId, int userId, long amount, LocalDate dateOfSubscription, LocalDate validity,
			boolean approved) {

		this.userId = userId;
		this.amount = amount;
		this.dateOfSubscription = dateOfSubscription;
		this.validity = validity;
		this.approved = approved;
		this.subscriptionId = subscriptionId;
	}
}
