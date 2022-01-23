package com.fastrack.msorderproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fastrack.msorderproject.models.Orders;



public interface OrderRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders>{

	List<Orders> findByName(String name);

}
