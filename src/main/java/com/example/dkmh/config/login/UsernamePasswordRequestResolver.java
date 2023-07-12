package com.example.dkmh.config.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dkmh.model.account.NormalAccount;
import com.example.dkmh.respository.database1.NormalAccountRepository;
import com.example.dkmh.service.database1.NormalAccountService;

@Service
public class UsernamePasswordRequestResolver implements LoginRequestResolver {
	@Autowired
	private NormalAccountService normalAccountService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public boolean valid(LoginRequest loginRequest) {
		if (!(loginRequest instanceof UsernamePasswordLoginRequest))
			throw new RuntimeException("Request invalid");
		UsernamePasswordLoginRequest usernamePasswordLoginRequest = (UsernamePasswordLoginRequest) loginRequest;
		if (!this.validUserName(usernamePasswordLoginRequest.getUsername()))
			return false;
		if (!this.validPassword(usernamePasswordLoginRequest.getUsername(), usernamePasswordLoginRequest.getPassword()))
			return false;

		NormalAccount newAccount = normalAccountService.findByUsername(usernamePasswordLoginRequest.getUsername())
				.get();
		newAccount.getLoginRequests().add(usernamePasswordLoginRequest);
		normalAccountService.save(newAccount);

		return true;
	}

	private boolean validUserName(String username) {
		return normalAccountService.existsByUsername(username);
	}

	private boolean validPassword(String username, String password) {
		return passwordEncoder.matches(password, normalAccountService.findByUsername(username).get().getPassword());
	}

}
