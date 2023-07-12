package com.example.dkmh.model.product;

import java.util.concurrent.ThreadLocalRandom;

import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

public class ProductGenneratorID implements ValueGenerator<String> {
	@Override
	public String generateValue(Session session, Object owner) {
		Product product = (Product) owner;
		return (product.getName().charAt(0) + ThreadLocalRandom.current().nextInt(1000, 9999) + "").toUpperCase();
	}

}
