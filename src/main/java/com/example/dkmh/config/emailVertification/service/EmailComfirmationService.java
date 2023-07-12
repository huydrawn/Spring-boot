package com.example.dkmh.config.emailVertification.service;

import com.example.dkmh.config.emailVertification.EmailComfirmation;

public interface EmailComfirmationService {
	boolean existsByToken(String token);

	EmailComfirmation findByToken(String token);

	boolean existsByEmail(String email);

	void save(EmailComfirmation emailComfirmation);

	void sendEmailVertification(String name, String token, String to);
}
