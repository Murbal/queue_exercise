package com.emirhangueler.processor.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueConfiguration {
    public static final String queueName = "queue_exercise";

    @Bean
	public Queue queue() {
		return new Queue(QueueConfiguration.queueName);
	}
}
