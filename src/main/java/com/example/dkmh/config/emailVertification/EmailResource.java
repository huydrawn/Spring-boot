package com.example.dkmh.config.emailVertification;

import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import com.example.dkmh.config.emailVertification.service.EmailComfirmationService;
import com.example.dkmh.config.register.RegisterRequest;
import com.example.dkmh.model.account.NormalAccount;
import com.example.dkmh.service.database1.NormalAccountService;

@RestController
@RequestMapping("/email")
public class EmailResource {
	@Autowired
	EmailComfirmationService comfirmationService;

	@Autowired
	NormalAccountService normalAccountService;
	@Autowired
	EmailComfirmationService emailComfirmationService;

	@Autowired
	TemplateEngine t;

	@GetMapping("/vertification/{token}")
	public ResponseEntity<EmailVertificactionResponse> vertification(@PathVariable String token) {
		EmailComfirmation emailComfirmation = comfirmationService.findByToken(token);
		emailComfirmation.active();
		return ResponseEntity.ok(new EmailVertificactionResponse("Vertification Success", emailComfirmation));
	}

	@GetMapping("/send/{id}")
	public ResponseEntity<String> send(@PathVariable String id) {
		Optional<NormalAccount> n = normalAccountService.findByID(Integer.valueOf(id));
		if (n.isPresent()) {
			emailComfirmationService.sendEmailVertification("huy", n.get().getEmailComfirmation().getToken(),
					n.get().getEmailComfirmation().getEmail());
			return ResponseEntity.ok("success");
		}
		return ResponseEntity.ok("Failure");

	}
}
