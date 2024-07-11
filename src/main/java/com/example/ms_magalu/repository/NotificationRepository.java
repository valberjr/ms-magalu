package com.example.ms_magalu.repository;

import com.example.ms_magalu.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
