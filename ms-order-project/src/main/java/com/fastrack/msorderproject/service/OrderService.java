package com.fastrack.msorderproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastrack.msorderproject.dto.OrderDto;
import com.fastrack.msorderproject.models.Orders;
import com.fastrack.msorderproject.models.StatusEnum;
import com.fastrack.msorderproject.producer.OrderProducer;
import com.fastrack.msorderproject.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderProducer orderProducer;
	
	
	public OrderDto createOrder(OrderDto body) {
		Orders order = new Orders(body.getDescription(), body.getName(), body.getTotal(), body.getStatus());
		orderRepository.save(order);

		OrderDto orderDto = new OrderDto(order);
		orderProducer.send(orderDto);
		
		return orderDto;
	}
	
	
	public Optional<Orders> findById(Long id){
		return orderRepository.findById(id);
	}
	
	public List<Orders> findAll(){
		return orderRepository.findAll();
	}
	
	public List<Orders> findAll(String q, Double minTotalDouble, Double maxTotalDouble, StatusEnum status){
		return orderRepository.findAll(q, minTotalDouble, maxTotalDouble, status);
	}

	
	
	public void deleteOrder(Optional<Orders> order) {
		orderRepository.delete(order.get());
	}

}
