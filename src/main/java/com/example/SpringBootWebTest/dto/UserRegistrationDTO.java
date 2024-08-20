package com.example.SpringBootWebTest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class UserRegistrationDTO {
	private String username;
	private String password;
}
