package com.kt.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.domain.user.User;
import com.kt.dto.UserCreateRequest;
import com.kt.repository.UserJDBCRepository;
import com.kt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	private final UserJDBCRepository userJDBCRepository;
	private final UserRepository userRepository;

	public void create(UserCreateRequest request) {

		var newUser = new User(
			request.loginId(),
			request.password(),
			request.name(),
			request.email(),
			request.mobile(),
			request.gender(),
			request.birthday(),
			LocalDateTime.now(),
			LocalDateTime.now()
		);

		userRepository.save(newUser);
	}

	// public boolean isDuplicateLoginId(String loginId) {
	// 	return userJDBCRepository.existsByLoginId(loginId);
	// }

	public void changePassword(Long id, String oldPassword, String password) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

		if(!user.getPassword().equals(oldPassword)) {
			throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");
		}

		if(oldPassword.equals(password)) {
			throw new IllegalArgumentException("기존 비밀번호와 동일한 비밀번호로 변경할 수 없습니다.");
		}

		user.changePassword(password);
	}

	public Page<User> search(Pageable pageable, String keyword) {
		return userRepository.findAllByNameContaining(pageable, keyword);
	}

	public User detail(Long id) {
		return userRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
	}

	public void update(Long id, String name, String email, String mobile) {
		var user = userRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

		user.update(id, name, email, mobile);
	}
}
