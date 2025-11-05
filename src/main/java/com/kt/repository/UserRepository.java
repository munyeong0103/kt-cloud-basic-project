package com.kt.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import com.kt.domain.Gender;
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

	public boolean existsByLoginId(String loginId) {
		var sql = "SELECT EXISTS (SELECT id FROM MEMBER WHERE loginId = ?)";
		return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, loginId));
	}

	public void updatePassword(long id, String password) {
		var sql = "UPDATE MEMBER SET password = ? WHERE id = ?";
		jdbcTemplate.update(sql, password, id);
	}

	public boolean existsById(long id) {
		var sql = "SELECT EXISTS (SELECT id FROM MEMBER WHERE id = ?)";
		return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, id));
	}

	public Optional<User> selectById(long id) {
		var sql = "SELECT * FROM MEMBER WHERE id = ?";
		// ResultSet객체로 반환을 함

		var list = jdbcTemplate.query(sql, rowMapper(), id);

		return list.stream().findFirst();
	}

	public Pair<List<User>, Long> selectAll(int page, int size) {
		// paging의 구조
		// 백엔드 입장에서 필요한 것
		// 한화면에 몇개 보여줄것인가? => limit
		// 내가 몇번째 페이지를 보고있나? => offset (몇개를 건너뛸것인가?)
		// 보고있는 페이지 - 1 * limit
		var sql = "SELECT * FROM MEMBER LIMIT ? OFFSET ?";

		var users = jdbcTemplate.query(sql, rowMapper(), page, size);

		var countSql = "SELECT COUNT(*) FROM MEMBER";
		var totalElements = jdbcTemplate.queryForObject(countSql, Long.class);

		return Pair.of(users, totalElements);
	}

	private RowMapper<User> rowMapper() {
		return (rs, rowNum) -> mapToUser(rs);
		// () -> {} 람다는 단일 실행문이면 {} 와 return 생략이 가능하다
	}

	private User mapToUser(ResultSet rs) throws SQLException {
		return new User(
			rs.getLong("id"),
			rs.getString("loginId"),
			rs.getString("password"),
			rs.getString("name"),
			rs.getString("email"),
			rs.getString("mobile"),
			Gender.valueOf(rs.getString("gender")),
			rs.getObject("birthday", LocalDate.class),
			rs.getObject("createdAt", LocalDateTime.class),
			rs.getObject("updatedAt", LocalDateTime.class)
		);
	}
}
