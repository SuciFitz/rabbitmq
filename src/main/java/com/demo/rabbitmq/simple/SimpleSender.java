package com.demo.rabbitmq.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author szh 2020/6/17
 */
public class SimpleSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSender.class);

    @Autowired
    private RabbitTemplate template;

    private static final String QUEUE_NAME = "simple.hello";

    public void send() {
        String message = "Hello World!";
        this.template.convertAndSend(QUEUE_NAME, message);
        LOGGER.info(" [x] Sent '{}'", message);
    }
}