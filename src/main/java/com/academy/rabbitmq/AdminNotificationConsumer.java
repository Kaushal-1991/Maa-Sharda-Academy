package com.academy.rabbitmq;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.academy.config.RabbitMQConfig;
import com.academy.dto.StudentRegisteredEvent;
import com.academy.entity.Notification;
import com.academy.reposistory.NotificationRepository;

@Component
public class AdminNotificationConsumer {

    private final NotificationRepository notificationRepository;

    public AdminNotificationConsumer(
            NotificationRepository notificationRepository) {

        this.notificationRepository = notificationRepository;
    }

    @RabbitListener(
            queues = RabbitMQConfig.QUEUE
    )
    public void handleStudentRegistered(
            StudentRegisteredEvent event) {

        Notification notification =
                new Notification();

        notification.setTitle("New Student Registration");

        notification.setMessage(
                event.getStudentName()
                + " registered successfully"
        );

        notification.setRead(false);

        notification.setCreatedAt(
                LocalDateTime.now()
        );

        notificationRepository.save(notification);

        System.out.println(
                "Admin notification created for "
                + event.getStudentName()
        );
    }
}
