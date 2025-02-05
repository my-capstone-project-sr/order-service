package com.sr.order_service.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="order_details")
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="order_status")
	private String orderStatus;
	
	@Column(name="order_date")
	private LocalDate orderDate;
	
	@Column(name="order_user_id")
	private int orderUserId;
	
	@Column(name="order_store_id")
	private int orderStoreId;

	@Column(name="total_amount")
	private double totalAmount;

}
