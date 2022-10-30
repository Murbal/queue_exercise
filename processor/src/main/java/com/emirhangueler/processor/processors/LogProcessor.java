package com.emirhangueler.processor.processors;

import org.springframework.stereotype.Component;

/**
 * logs the received message
 */
@Component
public final class LogProcessor implements Processor {
    public void process(final String message) {
        System.out.println("Message <%s>".formatted(message));
    }
}
