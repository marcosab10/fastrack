package com.fastrack.msorderproject.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.fastrack.msorderproject.dto.OrderDto;
import com.fastrack.msorderproject.models.Orders;
import com.fastrack.msorderproject.repository.OrderRepository;


@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	

	public ResponseEntity<OrderDto> save(@RequestBody @Validated OrderDto orderDto, UriComponentsBuilder uriBuilder) {
		Orders ordem = orderDto.converter();
		orderRepository.save(ordem);
		URI uri = uriBuilder.path("/listaOrdens/{id}").buildAndExpand(ordem.getId()).toUri();
		
		//orderProducer.send(orderIn);
		
		
		return ResponseEntity.created(uri).body(new OrderDto(ordem));
	}
	

}
