package com.example.dkmh.controller.response;

public class RegisterRequest {
	private String username;
	private String password;
	private String email;

	public String getUsername() {
		return username;
	}

	public RegisterRequest(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
}
