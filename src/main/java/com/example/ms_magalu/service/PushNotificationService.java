package com.example.ms_magalu.service;

import com.example.ms_magalu.dto.NotificationDto;
import com.example.ms_magalu.service.strategy.NotificationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("push")
public class PushNotificationService implements NotificationStrategy {
    private final Logger logger = LoggerFactory.getLogger(PushNotificationService.class);

    @Override
    public void sendNotification(NotificationDto dto) {
        logger.info("Push notification: {}", dto);
    }
}
