package com.fastrack.msorderproject.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fastrack.msorderproject.dto.OrderDto;
import com.fastrack.msorderproject.models.Orders;
import com.fastrack.msorderproject.repository.OrderRepository;


@RestController
@Component
public class OrderController {
	
private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Value("${topic.name}")
	private String topic;
	
	@Value("${spring.kafka.group-id}")
	private String groupId;
	
	@Autowired
	private OrderRepository orderRepository;
	
	OrderDto ordemForm;
	
	@Transactional
	@KafkaListener(topics="${topic.name}", groupId = "${spring.kafka.group-id}", containerFactory = "orderKafkaListenerContainerFactory")
	public void listenTopicOrder(ConsumerRecord<String, OrderDto> record) {
		log.info("Received Message Partition: " + record.partition());
		log.info("Received Message: " + record.value());
		
		OrderDto od = record.value();
		String name = od.getName();
		
	
		List<Orders> list = orderRepository.findByName(name);
		
		List<OrderDto> odt = OrderDto.converter(list);
		
		for (OrderDto ordem : odt) {
			
			//Atualizar
		}
	}
	
	public void atualizar(Long id,@RequestBody @Validated OrderDto orderDto ){
		Optional<Orders> ordem =  orderRepository.findById(id);
		if (ordem.isPresent()) {
			
			
			//ordemForm.atualizar(id, orderRepository);
			System.out.println("++++++++++++++++++++++ SUCESSO ++++++++++++++++++++++++");
		}
	
	}
    
}
