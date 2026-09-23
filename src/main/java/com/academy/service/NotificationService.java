package com.academy.service;

import java.util.List;

import com.academy.entity.Notification;

public interface NotificationService {
	List<Notification> getAllNotifications();

    List<Notification> getUnreadNotifications();

    Notification getNotificationById(Long id);

    Notification saveNotification(Notification notification);

    Notification markAsRead(Long id);

    void markAllAsRead();

    void deleteNotification(Long id);
}
