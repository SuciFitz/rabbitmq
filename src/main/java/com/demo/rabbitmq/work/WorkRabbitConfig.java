package com.demo.rabbitmq.work;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author szh 2020/6/18
 */
@Configuration
public class WorkRabbitConfig {

    @Bean
    public Queue workQueue() {
        return new Queue("work.hello");
    }

    @Bean
    public WorkReceiver workReceiver1() {
        return new WorkReceiver(1);
    }

    @Bean
    public WorkReceiver workReceiver2() {
        return new WorkReceiver(2);
    }

    @Bean
    public WorkSender workSender() {
        return new WorkSender();
    }

}