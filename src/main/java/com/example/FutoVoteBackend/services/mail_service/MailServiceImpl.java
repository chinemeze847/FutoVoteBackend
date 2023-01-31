package com.example.FutoVoteBackend.services.mail_service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.FutoVoteBackend.dto.Mail;

@Service
@Slf4j
@Primary
public class MailServiceImpl implements MailService {
	@Autowired
	private final JavaMailSender javaMailSender;

	public MailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	@Async
	public void sendEmail(Mail mail) throws MessagingException {
		log.info("Send Email {}", mail);
		log.info("Sending Email to {}", mail.getTo());
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setTo(mail.getTo());
		mimeMessageHelper.setFrom(mail.getFrom());
		mimeMessageHelper.setText(mail.getContent(), true);
		mimeMessageHelper.setSubject(mail.getSubject());
		javaMailSender.send(mimeMessage);
		log.info("Email Sent Successfully");
	}
}

