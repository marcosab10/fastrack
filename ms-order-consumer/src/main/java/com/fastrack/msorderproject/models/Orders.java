package com.fastrack.msorderproject.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Orders{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double total;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	
	public Orders(String description, Long id, String name, double total, StatusEnum status) {
		this.name = name;
		this.total = total;
		this.status = status;
		this.description = description;
	}
	
}
