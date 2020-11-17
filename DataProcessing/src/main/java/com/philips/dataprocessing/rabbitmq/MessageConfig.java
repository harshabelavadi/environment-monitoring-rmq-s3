package com.philips.dataprocessing.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.philips.dataprocessing.Constants.StringConstants;

@Configuration
public class MessageConfig {

	@Bean
    public Queue queue() {
        return new Queue(StringConstants.QUEUE.get());
    }

	@Bean
    public Queue s3Queue() {
        return new Queue(StringConstants.S3QUEUE.get());
    }
	
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(StringConstants.EXCHANGE.get());
    }

    @Bean
    public Binding binding(TopicExchange exchange) {
        return BindingBuilder.bind(queue()).to(exchange).with(StringConstants.ROUTINGKEY.get());
    }
    
    @Bean
    public Binding s3binding(TopicExchange exchange) {
        return BindingBuilder.bind(s3Queue()).to(exchange).with(StringConstants.S3ROUTINGKEY.get());
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
