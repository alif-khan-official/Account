package com.user.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "username")
	private String username;
	
	@Column(name = "status")
	private boolean status = true;
	
	@Column(name = "account_id")
	private long accountId;
	
	/*
	public User() {
		
	}
	
	public User(long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}	
	*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
}
