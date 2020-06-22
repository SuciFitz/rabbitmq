package com.demo.rabbitmq.direct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author szh 2020/6/18
 */
public class DirectSender {

    @Autowired
    private RabbitTemplate template;

    private static final String EXCHANGE_NAME = "exchange.direct";

    private final String[] keys = {"orange", "black", "green"};

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectSender.class);

    public void send(int index) {
        int limitIndex = index % 3;
        String key = keys[limitIndex];
        String message = "Hello to " + key + ' ' +
                (index + 1);
        template.convertAndSend(EXCHANGE_NAME, key, message);
        LOGGER.info(" [x] Sent '{}'", message);
    }
}