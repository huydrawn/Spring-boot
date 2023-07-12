package com.example.dkmh.model.customer;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class CustomerFamilier extends Customer {

	public CustomerFamilier(String name, String email) {
		super(name, email);
		// TODO Auto-generated constructor stub
	}

	public CustomerFamilier() {
		super(null, null);
	}

}
