package com.transaction.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ledger")
public class Ledger {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "transaction_type")
	private String transactionType;
	
	@Column(name = "amount")
	private float amount;
	
	@Column(name = "date")
	private LocalDate date;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
}
