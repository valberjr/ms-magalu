package com.example.ms_magalu.service;

import com.example.ms_magalu.dto.ScheduleNotificationDto;
import com.example.ms_magalu.entity.Email;
import com.example.ms_magalu.entity.Notification;
import com.example.ms_magalu.entity.Status;
import com.example.ms_magalu.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    public NotificationService(NotificationRepository notificationRepository, EmailService emailService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    public void scheduleNotification(ScheduleNotificationDto dto) {
        notificationRepository.save(dto.toNotification());
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
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

    public void checkAndSend(LocalDateTime dateTime) {
        var notifications = notificationRepository.findByStatusInAndDateTimeBefore(
                List.of(Status.Values.PENDING.toStatus(), Status.Values.ERROR.toStatus()),
                dateTime
        );

        notifications.forEach(sendNotification());
    }

    private Consumer<Notification> sendNotification() {
        return notification -> {
            boolean sent = emailService.send(
                    new Email(
                            notification.getId(),
                            notification.getDestination(),
                            "MS-Magalu status notification",
                            notification.getMessage()
                    )
            );

            if (sent) {
                notification.setStatus(Status.Values.SUCCESS.toStatus());
            }

            notificationRepository.save(notification);
        };
    }

}
