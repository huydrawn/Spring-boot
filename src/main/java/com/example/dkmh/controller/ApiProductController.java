package com.example.dkmh.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dkmh.model.product.Product;
import com.example.dkmh.model.product.TypeProduct;
import com.example.dkmh.respository.database1.ProductRepository;
import com.example.dkmh.service.database1.ProductsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", methods = { RequestMethod.DELETE,
		RequestMethod.GET, RequestMethod.POST }, allowedHeaders = "*")
@RequestMapping("/product")
public class ApiProductController {
	@Autowired
	ProductsService productsService;

	@GetMapping("/getList")
	public ResponseEntity<List<Product>> getListProduct() {
		List<Product> list = productsService.getAll();
		
		return ResponseEntity.ok(list);
	}

	@PostMapping("/saveProduct")
	public ResponseEntity<String> saveProduct() {
		productsService.saveOrUpdate(new Product("LapTOp", 100000,
				"https://www.bing.com/images/search?view=detailV2&ccid=AR7Bx%2fJJ&id=834931FD4796D3955B4A5CA4F1A11C34D81EB4E4&thid=OIP.AR7Bx_JJ5SK8-CYiJKpmawHaFY&mediaurl=https%3a%2f%2fmspoweruser.com%2fwp-content%2fuploads%2f2016%2f08%2fMicromax-Alpha-Laptop-3.jpg&cdnurl=https%3a%2f%2fth.bing.com%2fth%2fid%2fR.011ec1c7f249e522bcf8262224aa666b%3frik%3d5LQe2DQcofGkXA%26pid%3dImgRaw%26r%3d0&exph=1333&expw=1835&q=laptop&simid=608013261876451635&FORM=IRPRST&ck=738DD929D8E99591274DAEB6EA0D253E&selectedIndex=0",
				TypeProduct.ELECTRONIC));
		return ResponseEntity.ok("123");
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
		productsService.delete(id);
		return ResponseEntity.ok("ok");
	}
}
