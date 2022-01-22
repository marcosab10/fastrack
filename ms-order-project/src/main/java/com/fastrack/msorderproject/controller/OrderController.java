package com.fastrack.msorderproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Void> createUsingPOST(OrderDto body) {
		Orders order = new Orders(body.getDescription(), body.getId(), body.getName(), body.getTotal(), body.getStatus());
		orderRepository.save(order);
		
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<OrderDto> deleteUsingDELETE(Long id) {
		Orders order = orderRepository.getById(id);
		orderRepository.delete(order);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<OrderDto> findByIdUsingGET(Long id) {
		Orders order = orderRepository.getById(id);
		OrderDto orderDto = new OrderDto(order);
		
		return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(orderDto);
	}

	@Override
	public ResponseEntity<List<OrderDto>> listUsingGET() {
		List<Orders> orders =  orderRepository.findAll();
		List<OrderDto> ordersDto = OrderDto.converter(orders);
		
		return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(ordersDto);
	}

	@Override
	public ResponseEntity<OrderDto> updateUsingPUT(OrderDto body, Long id) {		
		if(orderRepository.existsById(id)) {
			Orders order = orderRepository.getById(id);
			if(order.getDescription() != order.getDescription()) {
				order.setDescription(body.getDescription());
			}
			if(order.getName() != order.getName()) {
				order.setName(body.getName());
			}
			if(order.getTotal() != order.getTotal()) {
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
