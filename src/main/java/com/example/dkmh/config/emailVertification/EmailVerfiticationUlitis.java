package com.example.dkmh.config.emailVertification;

import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.google.api.client.util.Value;

@ConfigurationProperties(value = "classpath:/application.properties")
public class EmailVerfiticationUlitis {
	@Value("#'${server.port}'")
	public static String port;
	@Value("#'${spring.mail.username}'")
	public static String from;
	public final static String template = "emailvercation";

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public static String genneratePathVertification(String token) {
		return "localhost:" + port + "/email/vertification/" + token;
	}

}
