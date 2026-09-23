package com.academy.reposistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // All notifications - newest first
    List<Notification> findAllByOrderByCreatedAtDesc();

    // Only unread notifications - newest first
    List<Notification> findByIsReadFalseOrderByCreatedAtDesc();
}