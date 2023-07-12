package com.example.dkmh.model.bill;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.dkmh.model.product.Product;

@Entity
@Table(name = "BillImportProductDetails")
public class BillImportProductDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne(targetEntity = Product.class)
	private Product product;
	private int amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public BillImportProductDetails(int id, Product product, int amount) {
		super();
		this.id = id;
		this.product = product;
		this.amount = amount;
	}

}
