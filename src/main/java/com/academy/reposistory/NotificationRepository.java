package com.academy.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
