package com.example.dkmh.model.employee;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class NormalEmployee extends Employee implements Serializable {

	public NormalEmployee(String name, String numberPhone, String email) {
		super(name, numberPhone, email);
		// TODO Auto-generated constructor stub
	}

	public NormalEmployee() {
		super(null, null, null);
	}

}
