package com.example.dkmh.config.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.dkmh.model.account.NormalAccount;
import com.example.dkmh.service.database1.NormalAccountService;
import com.example.dkmh.service.database1.UserService;

@Component
public class AuthenticationUserProvider implements AuthenticationProvider {

	UserService userService;

	PasswordEncoder passwordEncoder;

	NormalAccountService normalAccountService;

	@Autowired
	public AuthenticationUserProvider(UserService userService, PasswordEncoder passwordEncoder,
			NormalAccountService normalAccountService) {
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.normalAccountService = normalAccountService;
	}

	@Override

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserDetails usersDetails = userService.loadUserByUsername(username);
		Optional<NormalAccount> account = normalAccountService.findByUsername(username);
		if (!account.isPresent() && !passwordEncoder.matches(password, account.get().getPassword()))
			throw new BadCredentialsException("Bad Credentials");
		return new UsernamePasswordAuthenticationToken(usersDetails, password, usersDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
