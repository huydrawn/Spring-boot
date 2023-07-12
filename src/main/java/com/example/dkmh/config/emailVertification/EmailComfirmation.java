package com.example.dkmh.config.emailVertification;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import com.example.dkmh.model.account.NormalAccount;

@Entity
public class EmailComfirmation {
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String email;
	private String token;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@OneToOne(cascade = CascadeType.ALL, targetEntity = NormalAccount.class)
	@JoinColumn(name = "Account")
	private NormalAccount normalAccount;
	public EmailComfirmation() {
		// TODO Auto-generated constructor stub
	}
	public EmailComfirmation(String email) {
		this.email = email;
		token = UUID.randomUUID().toString();
	}

	public void active() {
		normalAccount.setActive(true);
	}
}
