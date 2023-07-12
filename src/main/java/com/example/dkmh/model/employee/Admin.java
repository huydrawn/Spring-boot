package com.example.dkmh.model.employee;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class Admin extends Employee {
	public Admin( String name, String numberPhone, String email) {
		super(name, numberPhone, email);
	}

	public Admin() {
		super( null, null, null);
	}
}
