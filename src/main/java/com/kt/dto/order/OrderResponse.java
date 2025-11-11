package com.kt.dto.order;

import java.time.LocalDateTime;

import com.kt.domain.order.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;

public interface OrderResponse {
	record Search (
		Long id,
		String receiverName,
		String productName,
		Long quantity,
		Long totalPrice,
		OrderStatus status,
		LocalDateTime createdAt
	) {
		@QueryProjection
		public Search {

		}
	}
}
