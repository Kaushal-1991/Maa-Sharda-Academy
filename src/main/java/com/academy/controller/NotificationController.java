package com.academy.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.academy.entity.Notification;
import com.academy.service.NotificationService;


@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(
            NotificationService notificationService) {

        this.notificationService =
                notificationService;
    }


    // Get ALL notifications
    @GetMapping
    public ResponseEntity<List<Notification>>
    getAllNotifications() {

        return ResponseEntity.ok(
                notificationService.getAllNotifications()
        );
    }


    // Get ONLY unread notifications
    @GetMapping("/unread")
    public ResponseEntity<List<Notification>>
    getUnreadNotifications() {

        return ResponseEntity.ok(
                notificationService
                        .getUnreadNotifications()
        );
    }


    // Get notification by ID
    @GetMapping("/{id}")
    public ResponseEntity<Notification>
    getNotificationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                notificationService
                        .getNotificationById(id)
        );
    }


    // Save notification
    @PostMapping
    public ResponseEntity<Notification>
    saveNotification(
            @RequestBody Notification notification) {

        return ResponseEntity.ok(
                notificationService
                        .saveNotification(notification)
        );
    }


    // Mark single notification as read
    @PutMapping("/{id}/read")
    public ResponseEntity<Notification>
    markAsRead(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                notificationService
                        .markAsRead(id)
        );
    }


    // Mark ALL notifications as read
    @PutMapping("/read-all")
    public ResponseEntity<String>
    markAllAsRead() {

        notificationService.markAllAsRead();

        return ResponseEntity.ok(
                "All notifications marked as read"
        );
    }


    // Delete notification
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteNotification(
            @PathVariable Long id) {

        notificationService.deleteNotification(id);

        return ResponseEntity.ok(
                "Notification deleted successfully"
        );
    }
}
