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

    @Value("${rabbit.check.dead_queue}")
    private String checksDeadQueue;

    @Value("${rabbit.check.dead_exchange}")
    private String checksDeadExchange;

    @Value("${rabbit.check.dead_routing}")
    private String checksDeadRouting;

    @Bean
    public Queue checksQueue() {
        return QueueBuilder.durable(checksQueue)
                .withArgument("x-dead-letter-exchange", this.checksDeadExchange)
                .withArgument("x-dead-letter-routing-key", this.checksDeadRouting)
                .build();
    }

    @Bean
    public Queue checksDeadQueue() {
        return QueueBuilder.durable(this.checksDeadQueue).build();
    }

    @Bean
    public DirectExchange checksExchange() {
        return new DirectExchange(this.checksDeadExchange);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(this.checksDeadExchange);
    }

    @Bean
    public Binding checksBinding() {
        return BindingBuilder.bind(checksQueue())
                .to(checksExchange())
                .with(this.checksQueue);
    }

    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(checksDeadQueue())
                .to(deadLetterExchange())
                .with(this.checksDeadRouting);
    }

}
