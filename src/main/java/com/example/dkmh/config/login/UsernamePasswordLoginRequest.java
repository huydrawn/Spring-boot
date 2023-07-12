package com.example.dkmh.config.login;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class UsernamePasswordLoginRequest extends LoginRequest {
	private String username, password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsernamePasswordLoginRequest() {
		// TODO Auto-generated constructor stub
	}

	public UsernamePasswordLoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
