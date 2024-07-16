package com.example.ms_magalu.dto;

public record NotificationDto(Long notificationId,
                              String destination,
                              String subject,
                              String message,
                              String channel) {
}
