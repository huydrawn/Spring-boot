package com.example.dkmh.config.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.dkmh.respository.database1.UsersDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	private String jwtSecret = "123456";

	private int jwtExpirationMs = 100000;

	public String generateJwtToken(Authentication authentication) {

		UsersDetails userDetails = (UsersDetails) authentication.getPrincipal();

		return Jwts.builder().setSubject((userDetails.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public boolean validateJwtToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			System.out.println("Invalid JWT token: " + e.getMessage());
		}

		return false;
	}

	public String getUsernameFromJwtToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
}