package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}
