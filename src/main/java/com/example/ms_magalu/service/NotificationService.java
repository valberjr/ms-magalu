package com.example.ms_magalu.service;

import com.example.ms_magalu.dto.ScheduleNotificationDto;
import com.example.ms_magalu.entity.Notification;
import com.example.ms_magalu.entity.Status;
import com.example.ms_magalu.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(ScheduleNotificationDto dto) {
        notificationRepository.save(dto.toNotification());
    }

    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    public void cancelNotification(Long id) {
        var notification = notificationRepository.findById(id);

        if (notification.isPresent()) {
            notification.get().setStatus(Status.Values.CANCELLED.toStatus());
            notificationRepository.save(notification.get());
        }
    }
}
