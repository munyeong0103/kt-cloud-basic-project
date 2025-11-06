package com.kt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kt.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Boolean existsByLoginId(String loginId);

	@Query("""
	SELECT exists (SELECT u FROM User u WHERE u.loginId = ?1)
""")
	Boolean existsByLoginIdJPQL(String loginId);

	Page<User> findAllByNameContaining(Pageable pageable, String keyword);
}
