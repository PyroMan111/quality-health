package com.woniuxy.cxy.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    @Bean
    public Queue createEmailQueue() {
        // 创建一个队列。默认不是启动时候创建，是第一次发消息时候创建。
        //return new Queue("q-email");
        return QueueBuilder.durable("q-email").maxLength(10).build();
    }


    //创建一个简单队列，用来清理购物车
    @Bean
    public Queue queue2(){
        return QueueBuilder.durable("clearBuyCartQueue").build();
    }
}
