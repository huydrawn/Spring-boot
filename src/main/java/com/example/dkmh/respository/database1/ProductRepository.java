package com.example.dkmh.respository.database1;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.example.dkmh.model.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	public Slice<Product> findByNameIgnoreCase(String name, Pageable pageable);
}
