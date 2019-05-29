package com.dell.tsp.notification.config;


import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dell.tsp.notification.VO.*;
import com.dell.tsp.notification.utils.QueueConsumer;


@Configuration
@ConfigurationProperties
@EnableConfigurationProperties
public class RabbitMQConfig {

	private static final String LISTENER_METHOD = "processMessage";
	
	  @Value("${spring.rabbitmq.queue.name}") 
	  private String QUEUE;
	  
	  @Value("${spring.rabbitmq.uri}")
		private String RABBITMQ_URI;
	
	  @Bean 
	  Queue queue() { 
		  return QueueBuilder.durable(QUEUE)
				  .build(); 
		  }
	 
	  @Bean
	    public ConnectionFactory connectionFactory() {
	        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
	        connectionFactory.setUri(RABBITMQ_URI);
	        return connectionFactory;
	    }
	  
	  @Bean
	    public MessageConverter jsonMessageConverter(){
		  Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
	        jackson2JsonMessageConverter.setClassMapper(new ClassMapper() {

	            @Override
	            public Class<?> toClass(MessageProperties properties) {
	                return EmailPayloadTemplate.class;
	            }

	            @Override
	            public void fromClass(Class<?> clazz, MessageProperties properties) {

	            }

	        });
	        return jackson2JsonMessageConverter;
	        }
	  @Bean
	  SimpleMessageListenerContainer container(
			  MessageListenerAdapter listenerAdapter) {
			  SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
			  container.setConnectionFactory(connectionFactory());
			  container.setMessageConverter(jsonMessageConverter());
			  container.setQueueNames(QUEUE);
			  container.setConcurrentConsumers(1);
			  container.setMessageListener(listenerAdapter);
			  return container;
			 }
	  
	  @Bean
	  MessageListenerAdapter listenerAdapter(QueueConsumer consumer) {
		  MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(consumer, LISTENER_METHOD);
	        messageListenerAdapter.setMessageConverter(jsonMessageConverter());
	        return messageListenerAdapter;
	  }

	
}
