package com.lucas.pingwise.infrastructure.messaging;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbit.check.queue}")
    private String checksQueue;

    @Value("${rabbit.check.exchange}")
    private String checksExchange;

    @Value("${rabbit.check.routing}")
    private String checksRoutingKey;

    @Value("${rabbit.check.dead_queue}")
    private String checksDeadQueue;

    @Value("${rabbit.check.dead_exchange}")
    private String checksDeadExchange;

    @Value("${rabbit.check.dead_routing}")
    private String checksDeadRoutingKey;

    @Value("${rabbit.monitor.update.queue}")
    private String monitorUpdateQueue;

    @Value("${rabbit.monitor.update.exchange}")
    private String monitorUpdateExchange;

    @Value("${rabbit.monitor.update.routing}")
    private String monitorUpdateRoutingKey;

    @Value("${rabbit.monitor.update.dead_queue}")
    private String monitorUpdateDeadQueue;

    @Value("${rabbit.monitor.update.dead_exchange}")
    private String monitorUpdateDeadExchange;

    @Value("${rabbit.monitor.update.dead_routing}")
    private String monitorUpdateDeadRoutingKey;


    @Bean
    public Queue checksQueue() {
        return QueueBuilder.durable(checksQueue)
                .withArgument("x-dead-letter-exchange", checksDeadExchange)
                .withArgument("x-dead-letter-routing-key", checksDeadRoutingKey)
                .build();
    }

    @Bean
    public Queue checksDeadQueue() {
        return QueueBuilder.durable(checksDeadQueue).build();
    }

    @Bean
    public DirectExchange checksExchange() {
        return new DirectExchange(checksExchange);
    }

    @Bean
    public DirectExchange checksDeadExchange() {
        return new DirectExchange(checksDeadExchange);
    }

    @Bean
    public Binding checksBinding() {
        return BindingBuilder.bind(checksQueue())
                .to(checksExchange())
                .with(checksRoutingKey);
    }

    @Bean
    public Binding checksDeadBinding() {
        return BindingBuilder.bind(checksDeadQueue())
                .to(checksDeadExchange())
                .with(checksDeadRoutingKey);
    }


    @Bean
    public Queue monitorUpdateQueue() {
        return QueueBuilder.durable(monitorUpdateQueue)
                .withArgument("x-dead-letter-exchange", monitorUpdateDeadExchange)
                .withArgument("x-dead-letter-routing-key", monitorUpdateDeadRoutingKey)
                .build();
    }

    @Bean
    public Queue monitorUpdateDeadQueue() {
        return QueueBuilder.durable(monitorUpdateDeadQueue).build();
    }

    @Bean
    public DirectExchange monitorUpdateExchange() {
        return new DirectExchange(monitorUpdateExchange);
    }

    @Bean
    public DirectExchange monitorUpdateDeadExchange() {
        return new DirectExchange(monitorUpdateDeadExchange);
    }

    @Bean
    public Binding monitorUpdateBinding() {
        return BindingBuilder.bind(monitorUpdateQueue())
                .to(monitorUpdateExchange())
                .with(monitorUpdateRoutingKey);
    }

    @Bean
    public Binding monitorUpdateDeadBinding() {
        return BindingBuilder.bind(monitorUpdateDeadQueue())
                .to(monitorUpdateDeadExchange())
                .with(monitorUpdateDeadRoutingKey);
    }
}