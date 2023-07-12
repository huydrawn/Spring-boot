package com.example.dkmh.model.bill;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.dkmh.model.customer.Provider;
import com.example.dkmh.model.employee.Employee;

@Entity
@Table(name="BillImportProduct")
@PrimaryKeyJoinColumn(name = "ID")
public class BillImportProduct extends Bill {
	@OneToOne(targetEntity = Provider.class, cascade = CascadeType.ALL)
	private Provider provider;
	@OneToMany(targetEntity = BillImportProductDetails.class, cascade = CascadeType.ALL)
	private List<BillImportProductDetails> listDetails;

	public BillImportProduct(Employee em, Provider provider, List<BillImportProductDetails> listDetails,
			Date dateCreate) {
		super(em, dateCreate);
		this.provider = provider;
		this.setListDetails(listDetails);
		// TODO Auto-generated constructor stub
	}

	public List<BillImportProductDetails> getListDetails() {
		return listDetails;
	}

	public void setListDetails(List<BillImportProductDetails> listDetails) {
		this.listDetails = listDetails;
	}

}
