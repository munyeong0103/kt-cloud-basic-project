package com.kt.controller.order;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.Paging;
import com.kt.dto.order.OrderResponse;
import com.kt.repository.OrderRepository;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {
	private final OrderRepository orderRepository;

	@GetMapping
	public Page<OrderResponse.Search> search(
		@RequestParam(required = false) String keyword,
		@Parameter(hidden = true) Paging paging
	) {
		return orderRepository.search(keyword, paging.toPageable());
	}
}
