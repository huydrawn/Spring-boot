package com.example.dkmh.controller;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dkmh.config.authentication.AuthenticationUserProvider;
import com.example.dkmh.config.jwt.JwtResponse;
import com.example.dkmh.config.jwt.JwtUtils;
import com.example.dkmh.config.login.LoginRequest;
import com.example.dkmh.controller.response.ApiResponse;
import com.example.dkmh.controller.response.RegisterRequest;
import com.example.dkmh.model.account.Account;
import com.example.dkmh.model.account.NormalAccount;
import com.example.dkmh.model.exception.UsernameExitException;
import com.example.dkmh.model.product.Product;
import com.example.dkmh.model.user.User;
import com.example.dkmh.respository.database1.NormalAccountRepository;
import com.example.dkmh.respository.database1.ProductRepository;
import com.example.dkmh.respository.database1.UsersDetails;
import com.example.dkmh.service.database1.AccountService;
import com.example.dkmh.service.database1.ProductsService;

@Controller
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", methods = { RequestMethod.DELETE,
		RequestMethod.GET, RequestMethod.POST }, allowedHeaders = "*")
@RequestMapping("/api/auth")
public class HandleAuthenticationController {
	@Autowired
	AuthenticationUserProvider authenticationUserProvider;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	EntityManager entityManager;
	@Autowired
	AccountService accountService;
	@Autowired
	Environment environment;
	@Autowired
	ProductsService productsService;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	DataSource dataSource;

	@GetMapping("/get")
	public ResponseEntity<Product> get() {
		System.out.println(Arrays.toString(productsService.getProductsByName("LapTOp").toList().toArray()));
		return ResponseEntity.ok(new Product());
	}

	@GetMapping("/people")
	ResponseEntity<Collection<Product>> people(Pageable pageable, PagedResourcesAssembler assembler) {
		Page<Product> people = productRepository.findAll(pageable);
		try {
			System.out.println(dataSource.getConnection().getClientInfo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(assembler.toModel(people).getContent());
	}

	@GetMapping("/csrf-token")
	public ResponseEntity<CsrfToken> csrfToken(HttpServletRequest request, HttpServletResponse response) {
		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//		System.out.println(csrfToken.getToken() + " " + csrfToken.getHeaderName());
		return ResponseEntity.ok(csrfToken);
	}
//	@Autowired
//	@GetMapping("/getIf")
//	public ResponseEntity<Object> getIfo(RedisProperties redis , RedisTemplate<Object, Object> template){
////		System.out.println(template.o);
////		System.out.println(environment.getProperty("spring.redis.timeout"));
//		return ResponseEntity.ok(redis);
//	}

	@PostMapping("/logout")
	public ResponseEntity<Object> logout(HttpServletRequest request) {
		System.out.println("--------------------");
		SecurityContextHolder.clearContext();
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return ResponseEntity.ok(new ApiResponse(true, "Logout successful"));
	}
}
