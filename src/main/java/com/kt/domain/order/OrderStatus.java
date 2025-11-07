package com.kt.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
	PENDING("결제대기"),
	COMPLETED("결제완료"),
	CANCELED("주문취소"),
	SHIPPED("배송중"),
	DELIVERED("배송완료");

	private final String description;
}
