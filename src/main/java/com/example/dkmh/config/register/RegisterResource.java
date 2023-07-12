package com.example.dkmh.config.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dkmh.service.database1.NormalAccountService;

@Controller
@RequestMapping("/register")
public class RegisterResource {
	@Autowired
	RegisterResolver registerResolver;

	@Autowired
	NormalAccountService normalAccountService;

	@PostMapping("/regis")
	public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
		if (registerResolver.solve(registerRequest))
			return ResponseEntity.ok("success");
		return ResponseEntity.ok("Bad Register");
	}

	@GetMapping("/remove/{id}")
	public ResponseEntity<String> remove(@PathVariable String id) {
		normalAccountService.delete(Integer.valueOf(id));
		return ResponseEntity.ok("ok");
	}

}
