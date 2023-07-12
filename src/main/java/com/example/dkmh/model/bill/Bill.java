package com.example.dkmh.model.bill;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Target;

import com.example.dkmh.model.customer.Customer;
import com.example.dkmh.model.employee.Employee;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Bill")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int Id;
	@OneToOne(targetEntity = Employee.class, cascade = CascadeType.ALL)
	protected Employee em;

	@Temporal(TemporalType.DATE)
	private Date dateCreate;

	public Bill(Employee em, Date dateCreate) {
		super();
		this.em = em;

		this.dateCreate = dateCreate;
	}

	public Employee getEm() {
		return em;
	}

	public void setEm(Employee em) {
		this.em = em;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
}
