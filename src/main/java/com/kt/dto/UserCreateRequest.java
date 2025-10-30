package com.kt.dto;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kt.domain.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserCreateRequest(
	@NotBlank
	String loginId,
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^])[A-Za-z\\d!@#$%^]{8,}$")
	String password,
	@NotBlank
	String name,
	@NotBlank
	String email,
	@NotBlank
	@Pattern(regexp = "^(0\\d{1,2})-(\\d{3,4})-(\\d{4})$")
	String mobile,
	@NotNull
	Gender gender,
	@NotNull
	LocalDate birthday,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
){}
