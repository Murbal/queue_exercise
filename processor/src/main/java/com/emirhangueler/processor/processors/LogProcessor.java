package com.emirhangueler.processor.processors;

import org.springframework.stereotype.Component;

/**
 * logs the received message
 */
@Component
public final class LogProcessor implements Processor {
    public void process(final String message) {
        double r = Math.random() * 10000;
        System.out.println("Message %s".formatted(message));
        System.out.println("Wait for %.2f ms".formatted(r));
        try {
            Thread.sleep(((long) r));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Wait finished");
    }
}
