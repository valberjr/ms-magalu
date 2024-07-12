package com.example.ms_magalu.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long emailId;
    private Long notificationId;
    private String emailFrom;
    private String emailTo;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String message;
    private LocalDateTime sendDate;

    @Enumerated(EnumType.STRING)
    private StatusEmails status;

    public Email() {
    }

    public Email(Long notificationId, String emailTo, String subject, String message) {
        this.notificationId = notificationId;
        this.emailTo = emailTo;
        this.subject = subject;
        this.message = message;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public StatusEmails getStatus() {
        return status;
    }

    public void setStatus(StatusEmails status) {
        this.status = status;
    }

    public enum StatusEmails {
        SENT("sent"),
        ERROR("error");

        private String description;

        StatusEmails(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
