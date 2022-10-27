package com.emirhangueler.processor;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProcessorApplication {
	@Autowired
	private Receiver receiver;

	private final String queueName = "queue_exercise";

	public static void main(String[] args) {
		SpringApplication.run(ProcessorApplication.class, args);
	}

	@RabbitListener(queues = queueName)
	public void receiveMessage(String message) {
		this.receiver.receiveMessage(message);
	}

	@Bean
	public Queue queue() {
		return new Queue(this.queueName);
	}
}
