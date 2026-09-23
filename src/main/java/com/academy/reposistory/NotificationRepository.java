package com.academy.reposistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	List<Notification> findAllByOrderByCreatedAtDesc();
    List<Notification> findByReadFalseOrderByCreatedAtDesc();
}
