package com.example.dkmh.learn;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	public Phone(String number, Person person) {
		super();
		this.number = number;
		this.person = person;
	}

	private String number;
	@ManyToOne(targetEntity = Person.class)
	private Person person;

}
