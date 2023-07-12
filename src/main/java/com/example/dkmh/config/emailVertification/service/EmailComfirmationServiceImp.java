package com.example.dkmh.config.emailVertification.service;

import static com.example.dkmh.config.emailVertification.EmailVerfiticationUlitis.genneratePathVertification;

import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.dkmh.config.emailVertification.EmailComfirmation;
import com.example.dkmh.config.emailVertification.EmailVerfiticationUlitis;
import com.example.dkmh.config.emailVertification.repository.EmailComfirmationRepository;

@Service
@EnableAsync
public class EmailComfirmationServiceImp implements EmailComfirmationService {
	@Autowired
	JavaMailSender sendService;
	@Autowired
	TemplateEngine templateEngine;
	@Autowired
	EmailComfirmationRepository emailComfirmationRepository;

	@Override
	public boolean existsByToken(String token) {
		// TODO Auto-generated method stub
		return emailComfirmationRepository.existsByToken(token);
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return existsByEmail(email);
	}

	@Override
	public EmailComfirmation findByToken(String token) {
		// TODO Auto-generated method stub
		return emailComfirmationRepository.findByToken(token);
	}

	@Override
	public void save(EmailComfirmation emailComfirmation) {
		emailComfirmationRepository.save(emailComfirmation);
	}

	@Override
	@Async
	public void sendEmailVertification(String name, String token, String to) {

		MimeMessage mimeMessage = sendService.createMimeMessage();
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(mimeMessage, true);
			messageHelper.setPriority(1);
			messageHelper.setSubject("Vertifycation Email");
			messageHelper.setFrom("huy112233778899@gmail.com");
			messageHelper.setTo(to);
			Context context = new Context();
			context.setVariables(Map.of("url", genneratePathVertification(token)));
			String text = templateEngine.process("emailvercation", context);

			MimeMultipart mimeMultipart = new MimeMultipart();

			BodyPart content = new MimeBodyPart();
			content.setContent(text, "text/html");
			mimeMultipart.addBodyPart(content);

			BodyPart images = new MimeBodyPart();

			DataSource dataSource = new FileDataSource("C:\\Users\\Huy11\\OneDrive\\Pictures\\Screenshots\\huy.png");
			images.setDataHandler(new DataHandler(dataSource));
			images.setHeader("Content-ID", "images");
			mimeMultipart.addBodyPart(images);
			mimeMessage.setContent(mimeMultipart);
			sendService.send(mimeMessage);
//			messageHelper.setText(text, true);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
