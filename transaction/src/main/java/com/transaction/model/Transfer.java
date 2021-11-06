package com.transaction.model;

public class Transfer {
	
	private long userId;

	private float amount;

	private long transferToUserId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public long getTransferToUserId() {
		return transferToUserId;
	}

	public void setTransferToUserId(long transferToUserId) {
		this.transferToUserId = transferToUserId;
	}

}
