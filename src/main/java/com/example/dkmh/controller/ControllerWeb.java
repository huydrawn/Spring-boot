package com.example.dkmh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dkmh.function.admin.FunctionAdmin;
import com.example.dkmh.model.account.NormalAccount;
import com.example.dkmh.model.employee.Admin;
import com.example.dkmh.model.user.User;
import com.example.dkmh.respository.database1.NormalAccountRepository;
import com.example.dkmh.respository.database1.UserReposistory;
import com.example.dkmh.service.database1.AccountServiceImp;
import com.example.dkmh.service.database1.UserService;

@Controller
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", methods = { RequestMethod.DELETE,
		RequestMethod.GET, RequestMethod.POST }, allowedHeaders = "*")
public class ControllerWeb {
	@Autowired
	AccountServiceImp accountRepository;
	@Autowired
	FunctionAdmin functionAdmin;
	@Autowired
	UserReposistory userReposistory;
	@Autowired
	NormalAccountRepository normalAccountRepository;
	@Autowired
	UserService userService;

	@GetMapping("/visited")
	public ResponseEntity<List<Time>> visit() {
		List<Time> list = new ArrayList<>();
		list.add(new Time("okok", 3));
		list.add(new Time("123ok", 3));
		return ResponseEntity.ok(list);
	}

	@MessageMapping("chat/{to}/{message}")
	public void sendMessage(@PathVariable("message") String message) {

	}

	@GetMapping("/saveAccount")
	public void saveAcount() {

	}

	@GetMapping("/show")
	public String show(HttpSession session) {

		return "ok";
	}

	@GetMapping("/")
	public String updateAccount(HttpSession session) {
		return "unlog/home";
	}
}
