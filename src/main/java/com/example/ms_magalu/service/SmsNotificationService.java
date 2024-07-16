package com.example.ms_magalu.service;

import com.example.ms_magalu.dto.NotificationDto;
import com.example.ms_magalu.service.strategy.NotificationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("sms")
public class SmsNotificationService implements NotificationStrategy {
    private final Logger logger = LoggerFactory.getLogger(SmsNotificationService.class);

    @Override
    public void sendNotification(NotificationDto dto) {
        logger.info("SMS notification: {}", dto);
    }
}
