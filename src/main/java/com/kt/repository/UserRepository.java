package com.kt.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kt.domain.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
	private final JdbcTemplate jdbcTemplate;

	public void save(User user) {
		var sql = "INSERT INTO MEMBER (id, loginId, password, name, email, mobile, gender, birthday, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getId(), user.getLoginId(), user.getPassword(), user.getName(), user.getEmail(),
			user.getMobile(), user.getGender().name(), user.getBirthday(), user.getCreatedAt(), user.getUpdatedAt());
	}

	public long selectMaxId(){
		var sql = "SELECT MAX(id) FROM MEMBER";

		var maxId = jdbcTemplate.queryForObject(sql, Integer.class);
		return maxId == null ? 0L : maxId;
	}
}
