package com.example.ms_magalu.service.strategy;

import com.example.ms_magalu.dto.NotificationDto;

public interface NotificationStrategy {

    void sendNotification(NotificationDto dto);
}
