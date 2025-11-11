package com.kt.domain.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Receiver {
	@Column(name = "receiver_name")
	private String name;
	@Column(name = "receiver_address")
	private String address;
	@Column(name = "receiver_mobile")
	private String mobile;
}
