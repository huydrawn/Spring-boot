package com.example.dkmh.model.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Table(name = "Product")
@Entity
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
	@GenericGenerator(name = "product_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "product_sequence"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "3"),
			@Parameter(name = "optimizer", value = "pooled-lo") })
	private int id;
	private String name;
	private double price;
	@Column(columnDefinition = "text")
	private String linkImages;
	@Enumerated(EnumType.STRING)
	private TypeProduct typeProduct;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public TypeProduct getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(TypeProduct typeProduct) {
		this.typeProduct = typeProduct;
	}

	public String getLinkImages() {
		return linkImages;
	}

	public void setLinkImages(String linkImages) {
		this.linkImages = linkImages;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String name, double price, String linkImages, TypeProduct typeProduct) {
		super();
		this.name = name;
		this.price = price;
		this.linkImages = linkImages;
		this.typeProduct = typeProduct;
	}

}
