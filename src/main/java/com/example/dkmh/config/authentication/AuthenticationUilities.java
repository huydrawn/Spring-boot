package com.example.dkmh.config.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import com.example.dkmh.config.login.LoginRequest;
import com.example.dkmh.config.login.UsernamePasswordLoginRequest;

@Service
public class AuthenticationUilities {
	@Autowired
	AuthenticationUserProvider authenticationUserProvider;

	public Authentication create(UsernamePasswordLoginRequest request) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUsername(),
				request.getPassword());
		Authentication authentication = authenticationUserProvider.authenticate(token);
		return authentication;
	}

	public void saveAsCurrent(Authentication authentication) {
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
