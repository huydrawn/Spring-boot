package com.example.dkmh.config.emailVertification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dkmh.config.emailVertification.EmailComfirmation;

public interface EmailComfirmationRepository extends JpaRepository<EmailComfirmation, Integer> {

	public EmailComfirmation findByEmailIgnoreCase(String email);

	public EmailComfirmation findByToken(String token);

	boolean existsByToken(String token);

	boolean existsByEmail(String email);
}
