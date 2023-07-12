package com.example.dkmh.config.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dkmh.config.authentication.AuthenticationUilities;
import com.example.dkmh.config.jwt.JwtResponse;
import com.example.dkmh.config.jwt.JwtUtils;

@RestController
@RequestMapping("/login")
public class LoginResource {
	UsernamePasswordRequestResolver usernamePasswordRequestResolver;
	JwtUtils jwtUtils;
	AuthenticationUilities authenticationUilities;

	@Autowired
	public LoginResource(UsernamePasswordRequestResolver usernamePasswordRequestResolver, JwtUtils jwtUtils,
			AuthenticationUilities authenticationUilities) {
		super();
		this.usernamePasswordRequestResolver = usernamePasswordRequestResolver;
		this.jwtUtils = jwtUtils;
		this.authenticationUilities = authenticationUilities;
	}

	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> login(@RequestBody UsernamePasswordLoginRequest usernamePasswordLoginRequest) {
		if (usernamePasswordRequestResolver.valid(usernamePasswordLoginRequest)) {
			Authentication authentication = authenticationUilities.create(usernamePasswordLoginRequest);

			String jwt = jwtUtils.generateJwtToken(authentication);
			JwtResponse jwtResponse = new JwtResponse(jwt, usernamePasswordLoginRequest.getUsername());
			return ResponseEntity.ok(jwtResponse);
		}
		return ResponseEntity.ok(new JwtResponse(null, null));

	}
}
