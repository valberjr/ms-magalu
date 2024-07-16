package com.example.ms_magalu.service;

import com.example.ms_magalu.dto.NotificationDto;
import com.example.ms_magalu.service.strategy.NotificationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("whatsapp")
public class WhatsAppNotificationService implements NotificationStrategy {
    private final Logger logger = LoggerFactory.getLogger(WhatsAppNotificationService.class);

    @Override
    public void sendNotification(NotificationDto dto) {
        logger.info("Whatsapp notification: {}", dto);
    }
}
