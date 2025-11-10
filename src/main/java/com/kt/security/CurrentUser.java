package com.kt.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

//인가된 객체
@Getter
public class CurrentUser extends User {
	//jwt 파싱 통해 넣어주기
	private Long id;

	public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}


}
