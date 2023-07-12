package com.example.dkmh.model.employee;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.example.dkmh.model.user.User;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee extends User {
	public Employee( String name, String numberPhone, String email) {
		
		this.name = name;
		this.numberPhone = numberPhone;
		this.email = email;
	}

	protected String name;
	protected String numberPhone;
	protected String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
