package com.fastrack.msorderproject.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fastrack.msorderproject.dto.OrderDto;



@Service
public class OrderProducer {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);
	private final String topic;
	private final KafkaTemplate<String, OrderDto> kafkaTemplate;
	
  
  public OrderProducer(@Value("${topic.name}") String topic, KafkaTemplate<String, OrderDto> kafkaTemplate) {
	  this.topic = topic;
	  this.kafkaTemplate = kafkaTemplate;
  }

  public void send(OrderDto orderDTO) {
	  System.out.println("============= INICIO Template Topic =========== "+topic);
	  kafkaTemplate.send(topic, orderDTO).addCallback(
			   sucess -> logger.info("Mensagem Enviada" + sucess.getProducerRecord().value()),
			   failure -> logger.info("Mensagem Falhou" + failure.getMessage()));
	  
	  System.out.println("============= FIM Template ===========");
  }
}
