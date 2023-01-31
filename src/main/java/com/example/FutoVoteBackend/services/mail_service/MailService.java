package com.example.FutoVoteBackend.services.mail_service;

import javax.mail.MessagingException;

import com.example.FutoVoteBackend.dto.Mail;

public interface MailService {
	void sendEmail(Mail mail) throws MessagingException;
}
