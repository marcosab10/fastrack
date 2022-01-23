package com.fastrack.msorderproject.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fastrack.msorderproject.api.OrdersApi;
import com.fastrack.msorderproject.dto.OrderDto;
import com.fastrack.msorderproject.models.Orders;
import com.fastrack.msorderproject.repository.OrderRepository;

@RestController
public class OrderController implements OrdersApi{
	
	private static final String UTF_8 = "utf-8";
	private static final String DATA_ENCODING = "DataEncoding";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";
	
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public ResponseEntity<OrderDto> createUsingPOST(@Validated OrderDto body, UriComponentsBuilder uriBuilder) {
		Orders order = new Orders(body.getDescription(), body.getId(), body.getName(), body.getTotal(), body.getStatus());
		orderRepository.save(order);
		
		URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri();
		
		return ResponseEntity.created(uri).header(CONTENT_TYPE, APPLICATION_JSON).body(new OrderDto(order));
	}

	@Override
	public ResponseEntity<OrderDto> deleteUsingDELETE(Long id, UriComponentsBuilder uriBuilder) {
		if(orderRepository.existsById(id)) {
			Orders order = orderRepository.getById(id);
			orderRepository.delete(order);
			OrderDto orderDto = new OrderDto(order);	
			return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(orderDto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}

	@Override
	public ResponseEntity<OrderDto> findByIdUsingGET(Long id) {
		if(orderRepository.existsById(id)) {
			Orders order = orderRepository.getById(id);
			OrderDto orderDto = new OrderDto(order);	
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
	public ResponseEntity<OrderDto> updateUsingPUT(@Validated OrderDto body, Long id) {		
		if(orderRepository.existsById(id)) {
			Orders order = orderRepository.getById(id);
			if(order.getDescription() != body.getDescription()) {
				order.setDescription(body.getDescription());
			}
			if(order.getName() != body.getName()) {
				order.setName(body.getName());
			}
			if(order.getTotal() != body.getTotal()) {
				order.setTotal(body.getTotal());
			}
			if(order.getStatus().toString() != body.getStatus().toString()) {
				order.setStatus(body.getStatus());
			}
			
			orderRepository.save(order);
			
			OrderDto orderDto = new OrderDto(order);
			return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(orderDto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Override
	public ResponseEntity<List<OrderDto>> searchUsingGET(String maxTotal, String minTotal,
		 String status, String q) {
		List<Orders> orders = orderRepository.findFilters(q, Double.parseDouble(minTotal), Double.parseDouble(maxTotal));
		List<OrderDto> ordersDto = OrderDto.converter(orders);
		
		return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(ordersDto);
		
	}

	
    
}
