package com.example.dkmh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dkmh.function.admin.FunctionAdmin;
import com.example.dkmh.function.admin.FunctionAdminOperation;
import com.example.dkmh.model.account.Account;
import com.example.dkmh.model.account.GoogleAccount;
import com.example.dkmh.model.account.NormalAccount;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin(origins = "http://localhost:4200/")
public class AdminController {
	@GetMapping("/hello")
	public String helloAdmin(HttpSession session) {

		return "hello Admin :";
	}

	@PostMapping("/update")
	public String updateAccount(@RequestBody Account account) {
		FunctionAdmin functionAdmin = new FunctionAdminOperation();
		if (account instanceof NormalAccount) {
			NormalAccount acc = (NormalAccount) account;
			functionAdmin.updateAccount(acc);
		} else {
			GoogleAccount acc = (GoogleAccount) account;
			functionAdmin.updateAccount(acc);
		}
		return "ok";
	}
}
