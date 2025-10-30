package com.kt.dto;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kt.domain.Gender;

public record UserCreateRequest(
	String loginId,
	String password,
	String name,
	String email,
	String mobile,
	Gender gender,
	LocalDate birthday,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
){}
