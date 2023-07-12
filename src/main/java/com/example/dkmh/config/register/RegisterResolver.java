package com.example.dkmh.config.register;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dkmh.config.emailVertification.EmailComfirmation;
import com.example.dkmh.config.emailVertification.service.EmailComfirmationService;
import com.example.dkmh.model.account.NormalAccount;
import com.example.dkmh.model.user.User;
import com.example.dkmh.service.database1.NormalAccountService;

@Service
public class RegisterResolver {
	@Autowired
	NormalAccountService normalAccountService;
	@Autowired
	EmailComfirmationService emailComfirmationService;

	@Autowired
	PasswordEncoder passwordEncoder;

	public boolean solve(RegisterRequest registerRequest) {
		if (!this.saveAccount(registerRequest)) {

			return false;
		}
		EmailComfirmation emailComfirmation = normalAccountService.findByUsername(registerRequest.getUsername()).get()
				.getEmailComfirmation();
		emailComfirmationService.sendEmailVertification(registerRequest.getUsername(), emailComfirmation.getToken(),
				emailComfirmation.getEmail());
		return true;

	}

	private boolean saveAccount(RegisterRequest registerRequest) {
		Optional<NormalAccount> normalAccount = normalAccountService.findByUsername(registerRequest.getUsername());
		if (normalAccountService.existsByUsername(registerRequest.getUsername()) || (normalAccount.isPresent()
				&& normalAccount.get().getEmail().getEmail().equals(registerRequest.getEmail()))) {
			return false;
		} else {
			normalAccountService.save(new NormalAccount(new User(), registerRequest.getUsername(),
					passwordEncoder.encode(registerRequest.getPassword()),
					new EmailComfirmation(registerRequest.getEmail())));
			return true;
		}

	}
}
