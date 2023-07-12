package com.example.dkmh.model.account;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.dkmh.config.emailVertification.EmailComfirmation;
import com.example.dkmh.config.login.LoginRequest;
import com.example.dkmh.model.user.User;

@Entity
@DiscriminatorValue(value = "NormalAccount")
public class NormalAccount extends Account {

	@OneToMany(cascade = CascadeType.ALL, targetEntity = LoginRequest.class)
	private List<LoginRequest> loginRequests;

	@OneToOne(cascade = CascadeType.ALL, targetEntity = EmailComfirmation.class)
	private EmailComfirmation emailComfirmation;

	public NormalAccount(User user, String username, String password, EmailComfirmation email) {
		super(user);
		this.password = password;
		this.username = username;
		this.emailComfirmation = email;
		loginRequests = new ArrayList<>();
	}

	public EmailComfirmation getEmail() {
		return emailComfirmation;
	}

	public void setEmail(EmailComfirmation email) {
		this.emailComfirmation = email;
	}

	public NormalAccount() {
		super(null);
	}

	private String username;
	private String password;

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

	public List<LoginRequest> getLoginRequests() {
		return loginRequests;
	}

	public void setLoginRequests(List<LoginRequest> loginRequests) {
		this.loginRequests = loginRequests;
	}

	public EmailComfirmation getEmailComfirmation() {
		return emailComfirmation;
	}

	public void setEmailComfirmation(EmailComfirmation emailComfirmation) {
		this.emailComfirmation = emailComfirmation;
	}

}
