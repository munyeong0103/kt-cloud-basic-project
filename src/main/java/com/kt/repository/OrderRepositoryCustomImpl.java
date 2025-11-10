package com.kt.repository;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.kt.domain.order.Order;
import com.kt.dto.order.OrderResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;
	private final QOrder order = QOrder.order;
	private final QOrderProduct orderProduct = QOrderProduct.order;
	private final QProduct product = QProduct.product;

	@Override
	public Page<OrderResponse.Search> search(
		String keyword,
		Pageable pageable
	) {

		var booleanBuilder = new BooleanBuilder();
		booleanBuilder.and(containsProductName(keyword));

		var content = jpaQueryFactory
			.select(new QOrderResponse_Search(
				order.id,
				order.receiver.name,
				product.name,
				orderProduct.quantity,
				product.price.multiply(orderProduct.quantity),
				Expressions.asNumber(0L),
				order.status,
				order.product
			))
			.from(order)
			.join(orderProduct).on(orderProduct.order.id.eq(order.id))
			.join(product).on(orderProduct.product.id.eq(product.id))
			.where(booleanBuilder)
			.orderBy(order.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

			var total = (long) jpaQueryFactory.select(order.id)
				.from(order)
				.join(orderProduct).on(orderProduct.order.id.eq(order.id))
				.join(product).on(orderProduct.product.id.eq(product.id))
				.where(booleanBuilder)
				.fetch().size();

			return new PageImpl<>(content, pageable, total);
	}

	private BooleanExpression containsProductName(String keyword) {
		// if(Strings.isNotBlank(keyword)) {
		// 	return product.name.containsIgnoreCase(keyword);
		// } else {
		// 	return null;
		// }
		return Strings.isNotBlank(keyword) ? product.name.containsIgnoreCase(keyword) : null;
	}
}
