package com.example.ms_magalu.service;

import com.example.ms_magalu.dto.NotificationDto;
import com.example.ms_magalu.dto.ScheduleNotificationDto;
import com.example.ms_magalu.entity.Notification;
import com.example.ms_magalu.entity.Status;
import com.example.ms_magalu.repository.NotificationRepository;
import com.example.ms_magalu.service.strategy.NotificationStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository notificationRepository;
    private final NotificationStrategyFactory notificationStrategyFactory;

    public NotificationService(NotificationRepository notificationRepository, NotificationStrategyFactory notificationStrategyFactory) {
        this.notificationRepository = notificationRepository;
        this.notificationStrategyFactory = notificationStrategyFactory;
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
            var notificationDto = new NotificationDto(
                    notification.getId(),
                    notification.getDestination(),
                    notification.getSubject(),
                    notification.getMessage(),
                    notification.getChannel().getDescription()
            );

            notificationStrategyFactory.execute(notificationDto);
            notification.setStatus(Status.Values.SUCCESS.toStatus());
            notificationRepository.save(notification);
        };
    }

}
