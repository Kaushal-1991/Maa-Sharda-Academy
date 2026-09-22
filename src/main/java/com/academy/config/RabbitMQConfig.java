package com.academy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "student.exchange";

    public static final String QUEUE = "admin.notification.queue";

    public static final String ROUTING_KEY =
            "student.registered";

    @Bean
    public DirectExchange studentExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue adminNotificationQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public Binding notificationBinding(
            Queue adminNotificationQueue,
            DirectExchange studentExchange) {

        return BindingBuilder
                .bind(adminNotificationQueue)
                .to(studentExchange)
                .with(ROUTING_KEY);
    }
    
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}