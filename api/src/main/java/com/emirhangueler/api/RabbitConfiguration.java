package com.emirhangueler.api;

import java.net.URI;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
	private final String queueName = "queue_exercise";
	private final String user = System.getenv("RABBIT_USER");
	private final String password = System.getenv("RABBIT_PASSWORD");
	private final String hostname = System.getenv("RABBIT_HOST");
	private final int port = Integer.parseInt(System.getenv("RABBIT_PORT"));
	private final URI uri = URI.create("amqp://%s:%s@%s:%s".formatted(
			user,
			password,
			hostname,
			port));

	@Bean
	public CachingConnectionFactory connectionFactory() {
		return new CachingConnectionFactory(this.uri);
	}

	@Bean
	public Queue queue() {
		return new Queue(this.queueName);
	}
}
