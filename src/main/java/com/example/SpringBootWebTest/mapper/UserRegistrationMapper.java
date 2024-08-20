package com.example.SpringBootWebTest.mapper;

import com.example.SpringBootWebTest.dto.UserRegistrationDTO;
import com.example.SpringBootWebTest.model.User;
import org.springframework.stereotype.Component;


@Component
public class UserRegistrationMapper implements EntityDTOMapper<User, UserRegistrationDTO> {

	@Override
	public UserRegistrationDTO toDTO(User user) {
		return UserRegistrationDTO.builder().username(user.getUsername()).password(user.getPassword()).build();
	}

	@Override
	public User toEntity(UserRegistrationDTO dto) {
		return User.builder().username(dto.getUsername()).password(dto.getPassword()).build();
	}
}
