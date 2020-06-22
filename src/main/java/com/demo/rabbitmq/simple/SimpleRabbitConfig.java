package com.demo.rabbitmq.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author szh 2020/6/17
 */
@Configuration
public class SimpleRabbitConfig {

    @Bean
    public Queue hello() {
        return new Queue("simple.hello");
    }

    @Bean
    public SimpleSender simpleSender() {
        return new SimpleSender();
    }

    @Bean
    public SimpleReceiver simpleReceiver(){
        return new SimpleReceiver();
    }
}