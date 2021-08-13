package com.dhlg.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    // Fanout交换机
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout.ticket.exchange", true, false);
    }

    // 短信消息队列
    @Bean
    Queue fanoutSmsQueue() {
        return new Queue("fanout.sms.queue", true);
    }

    // 微信消息队列
    @Bean
    Queue fanoutWeChatQueue() {
        return new Queue("fanout.wechat.queue", true);
    }

    // 绑定队列和交换机
    @Bean
    Binding fanoutSmsBinding() {
        return BindingBuilder.bind(fanoutSmsQueue()).to(fanoutExchange());
    }

    @Bean
    Binding fanoutEmailBinding() {
        return BindingBuilder.bind(fanoutWeChatQueue()).to(fanoutExchange());
    }
}