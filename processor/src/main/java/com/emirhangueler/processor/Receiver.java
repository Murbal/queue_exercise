package com.emirhangueler.processor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emirhangueler.processor.configuration.QueueConfiguration;
import com.emirhangueler.processor.processors.LogProcessor;

/**
 * receives messages and distributes (in the future), depending on content, to processors
 */
@Component
public class Receiver {
    @Autowired
    private LogProcessor logProcessor;

    @RabbitListener(queues = QueueConfiguration.queueName)
    public void receiveMessage(String message) {
        this.logProcessor.process(message);
    }
}
