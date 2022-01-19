package com.fastrack.msorderproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fastrack.msorderproject.api.OrdersApi;
import com.fastrack.msorderproject.models.OrderDto;

@RestController
public class OrderController implements OrdersApi{

	@Override
	public ResponseEntity<Void> createUsingPOST(@Valid OrderDto body) {
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
		// TODO Auto-generated method stub
		System.out.println("listUsingGET");
		return null;
	}

	@Override
	public ResponseEntity<List<OrderDto>> searchUsingGET(@Valid String maxTotal, @Valid String minTotal,
			@Valid String status, @Valid String q) {
		// TODO Auto-generated method stub
		System.out.println("searchUsingGET");
		return null;
	}

	@Override
	public ResponseEntity<OrderDto> updateUsingPUT(@Valid OrderDto body, Long id) {
		// TODO Auto-generated method stub
		System.out.println("updateUsingPUT");
		return null;
	}

	
    
}
