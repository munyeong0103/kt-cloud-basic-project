package com.kt.domain.product;

import java.util.ArrayList;
import java.util.List;

import com.kt.common.BaseEntity;
import com.kt.domain.order.Order;
import com.kt.domain.orderproduct.OrderProduct;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Product extends BaseEntity {
	private String name;
	private Long price;
	private Long stock;
	@Enumerated(EnumType.STRING)
	private ProductStatus status;

	@OneToMany(mappedBy = "product")
	private List<OrderProduct> orderProducts = new ArrayList<>();
}
