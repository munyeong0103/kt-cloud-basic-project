package com.kt.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEMBER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "LOGINID")
	private String loginId;
	private String password;
	private String name;
	private String email;
	private String mobile;
	private Gender gender;
	private LocalDate birthday;
	@Column(name = "CREATEDAT")
	private LocalDateTime createdAt;
	@Column(name = "UPDATEDAT")
	private LocalDateTime updatedAt;

	public User(String loginId, String password, String name, String email, String mobile, Gender gender,
		LocalDate birthday, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.birthday = birthday;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public void changePassword(String newPassword) {
		this.password = newPassword;
	}

	public void update(Long id, String name, String email, String mobile) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}
}
