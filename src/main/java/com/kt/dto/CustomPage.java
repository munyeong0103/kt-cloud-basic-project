package com.kt.dto;

import java.util.List;

import com.kt.domain.User;

public record CustomPage(
	List<User> users,
	int size,
	int page,
	int pages,
	long totalElements
) {

}
