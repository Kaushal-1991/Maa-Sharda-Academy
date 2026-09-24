//package com.academy.rabbitmq;
//
//import java.time.LocalDateTime;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Component;
//
//import com.academy.config.RabbitMQConfig;
//import com.academy.dto.StudentRegisteredEvent;
//import com.academy.entity.Notification;
//import com.academy.reposistory.NotificationRepository;
//
//
//@Component
//public class StudentNotificationListener {
//
//    private final NotificationRepository notificationRepository;
//
//    private final SimpMessagingTemplate messagingTemplate;
//
//    public StudentNotificationListener(
//            NotificationRepository notificationRepository,
//            SimpMessagingTemplate messagingTemplate) {
//
//        this.notificationRepository = notificationRepository;
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    @RabbitListener(
//            queues = RabbitMQConfig.QUEUE
//    )
//    public void handleStudentRegistered(
//            StudentRegisteredEvent event) {
//
//        System.out.println(
//                "======================================"
//        );
//
//        System.out.println(
//                "RabbitMQ message received"
//        );
//
//        System.out.println(
//                "Student ID: " + event.getStudentId()
//        );
//
//        System.out.println(
//                "Student Name: " + event.getStudentName()
//        );
//
//        // --------------------------------
//        // 1. Save notification in database
//        // --------------------------------
//
//        Notification notification =
//                new Notification();
//
//        notification.setMessage(
//                "New student registered: "
//                + event.getStudentName()
//        );
//
//        notification.setRead(false);
//
//        notification.setCreatedAt(
//                LocalDateTime.now()
//        );
//
//        Notification savedNotification =
//                notificationRepository.save(notification);
//
//        System.out.println(
//                "Notification saved. ID: "
//                + savedNotification.getId()
//        );
//
//        // --------------------------------
//        // 2. Send notification to WebSocket
//        // --------------------------------
//
//        messagingTemplate.convertAndSend(
//                "/topic/notifications",
//                savedNotification
//        );
//
//        System.out.println(
//                "Notification sent to WebSocket"
//        );
//
//        System.out.println(
//                "======================================"
//        );
//    }
//}