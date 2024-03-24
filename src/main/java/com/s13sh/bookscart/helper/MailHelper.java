package com.s13sh.bookscart.helper;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.s13sh.bookscart.dto.User;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailHelper {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	TemplateEngine templateEngine;

	public boolean send(User user) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

		try {
			helper.setFrom("saishkulkarni7@gmail.com", "Books-Cart");
			helper.setTo(user.getEmail());
			helper.setSubject("Otp for Creating Account");

			Context context = new Context();
			context.setVariable("otp", user.getOtp());
			context.setVariable("name", user.getName());
			context.setVariable("gender", user.getGender());
			String emailContent = templateEngine.process("verify-otp", context);
			helper.setText(emailContent, true);
			mailSender.send(mimeMessage);
			return true;
		} catch (UnsupportedEncodingException | MessagingException e) {
			return false;
		}
	}

}
