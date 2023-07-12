package com.example.dkmh.model.customer;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.example.dkmh.model.user.User;

@Entity
@Table(name = "Customer")
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverride(name = "User_ID", column = @Column(name = "ID"))
public abstract class Customer extends User {

	protected Customer(String name, String email) {

		this.name = name;
		this.email = email;
		// TODO Auto-generated constructor stub
	}

	protected String name;
	protected String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
