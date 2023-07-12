package com.example.dkmh.config.emailVertification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class EmailVertificactionResponse {
	private LocalDateTime date;
	private String status;
	private EmailComfirmation comfirmation;

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public EmailComfirmation getComfirmation() {
		return comfirmation;
	}

	public void setComfirmation(EmailComfirmation comfirmation) {
		this.comfirmation = comfirmation;
	}

	public EmailVertificactionResponse(String status, EmailComfirmation comfirmation) {
		super();
		date = LocalDateTime.now();
		this.status = status;
		this.comfirmation = comfirmation;
	}

}
