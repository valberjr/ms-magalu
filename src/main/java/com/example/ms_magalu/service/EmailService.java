package com.example.ms_magalu.service;

import com.example.ms_magalu.entity.Email;
import com.example.ms_magalu.repository.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String emailFrom;

    public EmailService(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    @Transactional
    public boolean send(Email email) {
        boolean sentEmail = true;

        try {
            email.setSendDate(LocalDateTime.now());
            email.setEmailFrom(emailFrom);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email.getEmailTo());
            mailMessage.setSubject(email.getSubject());
            mailMessage.setText(email.getMessage());

            mailSender.send(mailMessage);

            email.setStatus(Email.StatusEmails.SENT);
            logger.info("Email sent successfully");
        } catch (MailException e) {
            logger.error("Error sending email: {}", e.getMessage());
            sentEmail = false;
            email.setStatus(Email.StatusEmails.ERROR);
        } finally {
            emailRepository.save(email);
        }

        return sentEmail;
    }
}
