package com.kt.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse<T> {
	private String code;
	private String message;
	private T data;

	public static <T> ApiResponse<T> ok(T data) {
		return ApiResponse.of("ok", "성공", data);
	}

	public static <T> ApiResponse<T> of(String code, String message, T data) {
		return new ApiResponse<>(code, message, data);
	}
}
