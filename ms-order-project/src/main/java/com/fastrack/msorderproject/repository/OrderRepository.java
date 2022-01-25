package com.fastrack.msorderproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.fastrack.msorderproject.models.Orders;
import com.fastrack.msorderproject.models.StatusEnum;



public interface OrderRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders>{
	
	@Query("SELECT o FROM Orders o WHERE (:q IS NULL OR LOWER (o.name) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER (o.description) LIKE LOWER(CONCAT('%', :q, '%')))"
			+ " AND (:maxTotal IS NULL OR o.total <= :maxTotal)"
			+ " AND (:minTotal IS NULL OR o.total >= :minTotal)"
			+ " AND (:status IS NULL OR o.status = :status)")
	public List<Orders> findAll(String q, Double minTotal, Double maxTotal, StatusEnum status);

}
