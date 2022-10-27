package com.emirhangueler.api;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Queue queue;

    public void send(String message) {
        this.rabbitTemplate.convertAndSend(queue.getName(), message);
    }
}
