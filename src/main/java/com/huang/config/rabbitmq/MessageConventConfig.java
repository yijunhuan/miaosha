package com.huang.config.rabbitmq;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//配置Rabbitmq的传送数据为json格式
@Configuration
public class MessageConventConfig {
    @Bean
    public MessageConverter messageConverterToJson() {
        return new Jackson2JsonMessageConverter();
    }
    
}
