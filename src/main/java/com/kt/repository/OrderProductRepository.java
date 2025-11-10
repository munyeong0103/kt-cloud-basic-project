package com.kt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.domain.orderproduct.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
