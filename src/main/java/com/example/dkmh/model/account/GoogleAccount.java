package com.example.dkmh.model.account;

import javax.persistence.Entity;

import com.example.dkmh.model.user.User;

@Entity
public class GoogleAccount extends Account {
	private String email;
	private String token;

	public GoogleAccount(User user, String email, String token) {
		super(user);
		this.email = email;
		this.token = token;
	}

	public GoogleAccount() {
		super(null);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
