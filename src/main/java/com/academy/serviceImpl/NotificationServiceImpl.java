package com.academy.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.entity.Notification;
import com.academy.reposistory.NotificationRepository;
import com.academy.service.NotificationService;


@Service
@Transactional
public class NotificationServiceImpl
        implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(
            NotificationRepository notificationRepository) {

        this.notificationRepository =
                notificationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Notification> getAllNotifications() {

        return notificationRepository
                .findAllByOrderByCreatedAtDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Notification> getUnreadNotifications() {

        return notificationRepository
                .findByIsReadFalseOrderByCreatedAtDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public Notification getNotificationById(Long id) {

        return notificationRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Notification not found with id: " + id
                        ));
    }

    @Override
    public Notification saveNotification(
            Notification notification) {

        notification.setRead(false);

        if (notification.getCreatedAt() == null) {
            notification.setCreatedAt(
                    java.time.LocalDateTime.now()
            );
        }

        return notificationRepository.save(notification);
    }

    @Override
    public Notification markAsRead(Long id) {

        Notification notification =
                getNotificationById(id);

        notification.setRead(true);

        return notificationRepository.save(notification);
    }

    @Override
    public void markAllAsRead() {

        List<Notification> notifications =
                notificationRepository
                        .findByIsReadFalseOrderByCreatedAtDesc();

        notifications.forEach(
                notification -> notification.setRead(true)
        );

        notificationRepository.saveAll(notifications);
    }

    @Override
    public void deleteNotification(Long id) {

        if (!notificationRepository.existsById(id)) {

            throw new RuntimeException(
                    "Notification not found with id: " + id
            );
        }

        notificationRepository.deleteById(id);
    }
}