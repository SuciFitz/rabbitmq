package com.demo.rabbitmq.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author szh 2020/6/18
 */
public class TopicSender {

    @Autowired
    private RabbitTemplate template;

    private static final String EXCHANGE_NAME = "exchange.topic";

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicSender.class);


    private final String[] keys = {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};

    public void send(int index) {
        int limitIndex = index%keys.length;
        String key = keys[limitIndex];
        String message = "Hello to " + key + ' ' + (index + 1);
        template.convertAndSend(EXCHANGE_NAME, key, message);
        LOGGER.info(" [x] Sent '{}'",message);
    }
}