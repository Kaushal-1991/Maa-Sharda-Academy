package com.academy.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.academy.config.RabbitMQConfig;
import com.academy.dto.StudentRegisteredEvent;

@Service
public class StudentNotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    public StudentNotificationProducer(
            RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendStudentRegisteredEvent(
            StudentRegisteredEvent event) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                event
        );
    }
}
