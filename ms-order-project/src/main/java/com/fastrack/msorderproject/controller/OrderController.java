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
	
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";
	
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public ResponseEntity<Void> createUsingPOST(OrderDto body) {
		System.out.println("createUsingPOST");
		return null;
	}

	@Override
	public ResponseEntity<OrderDto> deleteUsingDELETE(Long id) {
		// TODO Auto-generated method stub
		System.out.println("deleteUsingDELETE");
		return null;
	}

	@Override
	public ResponseEntity<OrderDto> findByIdUsingGET(Long id) {
		// TODO Auto-generated method stub
		System.out.println("findByIdUsingGET");
		return null;
	}

	@Override
	public ResponseEntity<List<OrderDto>> listUsingGET() {
		List<Orders> orders =  orderRepository.findAll();
		List<OrderDto> ordersDto = OrderDto.converter(orders);
		
		return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(ordersDto);
	}

	@Override
	public ResponseEntity<List<OrderDto>> searchUsingGET(String maxTotal, String minTotal,
		 String status, String q) {
		// TODO Auto-generated method stub
		System.out.println("searchUsingGET");
		return null;
	}

	@Override
	public ResponseEntity<OrderDto> updateUsingPUT(OrderDto body, Long id) {
		// TODO Auto-generated method stub
		System.out.println("updateUsingPUT");
		return null;
	}

	
    
}
