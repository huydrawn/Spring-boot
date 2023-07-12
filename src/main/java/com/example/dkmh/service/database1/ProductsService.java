package com.example.dkmh.service.database1;

import java.util.List;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.example.dkmh.model.product.Product;
import com.example.dkmh.respository.database1.ProductRepository;

@Service
@CacheConfig
public class ProductsService {
	@Autowired
	ProductRepository productRepository;

	@Cacheable(cacheNames = "product", key = "'allProducts'")
	public List<Product> getAll() {
		System.out.println("get from database");

		return productRepository.findAll();
	}

	@Caching(evict = { @CacheEvict(cacheNames = "product", key = "'allProducts'") }, put = {
			@CachePut(cacheNames = "product", key = "{#product.id}") })

	public void saveOrUpdate(Product product) {
		productRepository.save(product);
	}

	@Caching(evict = { @CacheEvict(cacheNames = "product", key = "'allProducts'"),
			@CacheEvict(cacheNames = "product", key = "{#id}") })
	public void delete(int id) {
		productRepository.deleteById(id);
	}

	public Slice<Product> getProductsByName(String name) {
		PageRequest p = PageRequest.of(0, 3);
		Product product = new Product();
		product.setName("LapTOp");
		
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("price", "linkImages", "typeProduct");
		Example<Product> a = Example.of(product, matcher);
		System.out.println(productRepository.findAll(a));
		return productRepository.findByNameIgnoreCase(name, p);
	}
}
