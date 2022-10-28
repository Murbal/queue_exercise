package com.emirhangueler.processor.processors;

import org.springframework.stereotype.Component;

/**
 * logs the received message
 */
@Component
public class LogProcessor {
    public void process(String message) {
        System.out.println("Received <%s>".formatted(message));
    }
}
