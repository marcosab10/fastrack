package com.fastrack.msorderproject.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fastrack.msorderproject.api.OrdersApi;
import com.fastrack.msorderproject.dto.OrderDto;
import com.fastrack.msorderproject.models.Orders;
import com.fastrack.msorderproject.producer.OrderProducer;
import com.fastrack.msorderproject.repository.OrderRepository;
import com.fastrack.msorderproject.validation.ValidatedParametersException;

@RestController
public class OrderController implements OrdersApi{
	
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderProducer orderProducer;
	
	@Override
	@Transactional
	public ResponseEntity<OrderDto> createUsingPOST(@Validated OrderDto body, UriComponentsBuilder uriBuilder) {
		Orders order = new Orders(body.getDescription(), body.getId(), body.getName(), body.getTotal(), body.getStatus());
		orderRepository.save(order);
		
		OrderDto orderDto = new OrderDto(order);
		
		orderProducer.send(orderDto);
		
		URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(uri).header(CONTENT_TYPE, APPLICATION_JSON).body(orderDto);
	}

	@Override
	@Transactional
	public ResponseEntity<OrderDto> deleteUsingDELETE(Long id, UriComponentsBuilder uriBuilder) {
		Optional<Orders> order = orderRepository.findById(id);
		if(order.isPresent()) {
			orderRepository.delete(order.get());
			OrderDto orderDto = new OrderDto(order.get());	
			return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(orderDto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}

	@Override
	public ResponseEntity<OrderDto> findByIdUsingGET(Long id) {
		Optional<Orders> order = orderRepository.findById(id);
		if(order.isPresent()) {
			OrderDto orderDto = new OrderDto(order.get());	
			return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(orderDto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<List<OrderDto>> listUsingGET() {
		List<Orders> orders =  orderRepository.findAll();
		List<OrderDto> ordersDto = OrderDto.converter(orders);
		
		return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(ordersDto);
	}

	@Override
	@Transactional
	public ResponseEntity<OrderDto> updateUsingPUT(@Validated OrderDto body, Long id) {		
		Optional<Orders> order = orderRepository.findById(id);
		if(order.isPresent()) {
			order.get().setDescription(body.getDescription());
			order.get().setName(body.getName());
			order.get().setTotal(body.getTotal());
			order.get().setStatus(body.getStatus());
		
			OrderDto orderDto = new OrderDto(order.get());
			return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(orderDto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Override
	public ResponseEntity<List<OrderDto>> searchUsingGET(String maxTotal, String minTotal,
		 String status, String q)  throws ValidatedParametersException {
		List<Orders> orders = orderRepository.findFilters(q, Double.parseDouble(minTotal), Double.parseDouble(maxTotal));
		List<OrderDto> ordersDto = OrderDto.converter(orders);
		
		return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(ordersDto);
		
	}

	
    
}
