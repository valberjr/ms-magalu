package com.example.ms_magalu.dto;

import com.example.ms_magalu.entity.Channel;
import com.example.ms_magalu.entity.Notification;
import com.example.ms_magalu.entity.Status;

import java.time.LocalDateTime;

public record ScheduleNotificationDto(LocalDateTime dateTime,
                                      String destination,
                                      String subject,
                                      String message,
                                      Channel.Values channel) {

    public Notification toNotification() {
        return new Notification(
                dateTime,
                destination,
                subject,
                message,
                channel.toChannel(),
                Status.Values.PENDING.toStatus()
        );
    }
}
