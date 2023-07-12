package com.example.dkmh.model.bill;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.dkmh.model.customer.Customer;
import com.example.dkmh.model.employee.Employee;

@Entity
@Table(name = "BillSellProduct")
@PrimaryKeyJoinColumn(name = "ID")
public class BillSellProduct extends Bill {
	@OneToOne(targetEntity = Customer.class, cascade = CascadeType.ALL)
	private Customer cus;
	@OneToMany(targetEntity = BillSellProductDetails.class, cascade = CascadeType.ALL)
	private List<BillSellProductDetails> listDetails;

	public BillSellProduct(Employee em, Customer cus, List<BillSellProductDetails> listDetails, Date dateCreate) {
		super(em, dateCreate);
		this.setCus(cus);
		this.listDetails = listDetails;
		// TODO Auto-generated constructor stub
	}

	public Customer getCus() {
		return cus;
	}

	public void setCus(Customer cus) {
		this.cus = cus;
	}

	public List<BillSellProductDetails> getListDetails() {
		return listDetails;
	}

	public void setListDetails(List<BillSellProductDetails> listDetails) {
		this.listDetails = listDetails;
	}

}
