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
import com.fastrack.msorderproject.models.StatusEnum;
import com.fastrack.msorderproject.service.OrderService;
import com.fastrack.msorderproject.validation.ValidatedParametersException;

@RestController
public class OrderController implements OrdersApi{
	
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";
	
	@Autowired
	private OrderService orderService;
	
	@Override
	@Transactional
	public ResponseEntity<OrderDto> createUsingPOST(@Validated OrderDto body, UriComponentsBuilder uriBuilder) {
		validateOrder(body);
		
		OrderDto orderDto = orderService.createOrder(body);
		
		URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(orderDto.getId()).toUri();
		return ResponseEntity.created(uri).header(CONTENT_TYPE, APPLICATION_JSON).body(orderDto);
	}

	@Override
	@Transactional
	public ResponseEntity<OrderDto> deleteUsingDELETE(Long id, UriComponentsBuilder uriBuilder) {
		Optional<Orders> order = orderService.findById(id);
		if(order.isPresent()) {
			orderService.deleteOrder(order);
			OrderDto orderDto = new OrderDto(order.get());	
			return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(orderDto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}

	@Override
	public ResponseEntity<OrderDto> findByIdUsingGET(Long id) {
		Optional<Orders> order = orderService.findById(id);
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
		List<Orders> orders =  orderService.findAll();
		List<OrderDto> ordersDto = OrderDto.converter(orders);
		
		return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(ordersDto);
	}

	@Override
	@Transactional
	public ResponseEntity<OrderDto> updateUsingPUT(@Validated OrderDto body, Long id) {		
		
		if(id == null || id < 1) {
			throw new ValidatedParametersException(id,  Long.class, "id", null, null);
		}
		
		validateOrder(body);
		
		Optional<Orders> order = orderService.findById(id);
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

	private void validateOrder(OrderDto body) {
		if(body.getDescription() == null || body.getName() == null || body.getTotal() == null || body.getStatus() == null || body.getTotal() == null
				|| "".equals(body.getDescription().trim()) ||  "".equals(body.getName().trim()) || "".equals(body.getStatus().toString().trim())
				|| body.getTotal() == 0.0 || body.getTotal() < 0.0) {
			throw new ValidatedParametersException(body,  Orders.class, "Order", null, null);
		}
	}
	
	@Override
	public ResponseEntity<List<OrderDto>> searchUsingGET(String maxTotal, String minTotal,
			StatusEnum status, String q)  throws ValidatedParametersException {
		Double maxTotalDouble = Double.MAX_VALUE;
		Double minTotalDouble = 0.0;
		
		try {
			if(maxTotal != null) {
				maxTotalDouble =  Double.parseDouble(maxTotal);
			}
			
		} catch (Exception e) {
			throw new ValidatedParametersException(maxTotal, Double.class, "max_total", null, e.getCause());
		}

		try {
			if(minTotal != null) {
				minTotalDouble =  Double.parseDouble(minTotal);
			}
			
		} catch (Exception e) {
			throw new ValidatedParametersException(minTotal,  Double.class, "min_total", null, e.getCause());
		}

		
		List<Orders> orders = orderService.findAll(q, minTotalDouble, maxTotalDouble, status);
		List<OrderDto> ordersDto = OrderDto.converter(orders);
		
		return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(ordersDto);
		
	}

	
    
}
