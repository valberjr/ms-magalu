package com.example.ms_magalu.service;

import com.example.ms_magalu.dto.NotificationDto;
import com.example.ms_magalu.entity.Email;
import com.example.ms_magalu.repository.EmailRepository;
import com.example.ms_magalu.service.strategy.NotificationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("email")
public class EmailNotificationService implements NotificationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationService.class);

    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String emailFrom;

    public EmailNotificationService(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    @Override
    public void sendNotification(NotificationDto dto) {
        var email = new Email(dto.notificationId(), dto.destination(), dto.subject(), dto.message());

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
            email.setStatus(Email.StatusEmails.ERROR);
        } finally {
            emailRepository.save(email);
        }
    }
}
