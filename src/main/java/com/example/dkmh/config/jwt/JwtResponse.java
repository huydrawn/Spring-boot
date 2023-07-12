package com.example.dkmh.config.jwt;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	private String jwt;
	private String name;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String jwt, String name) {
		super();
		this.jwt = jwt;
		this.name = name;
		
	}

}
