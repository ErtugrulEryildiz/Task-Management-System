package com.example.SpringBootWebTest.mapper;

import com.example.SpringBootWebTest.dto.StrippedUserResponseDTO;
import com.example.SpringBootWebTest.model.User;
import org.springframework.stereotype.Component;


@Component
public class StrippedUserResponseMapper implements EntityDTOMapper<User, StrippedUserResponseDTO> {
	private final TaskListResponseMapper mapper = new TaskListResponseMapper();

	@Override
	public StrippedUserResponseDTO toDTO(User user) {
		return StrippedUserResponseDTO.builder().id(user.getId()).username(user.getUsername()).build();
	}

	@Override
	public User toEntity(StrippedUserResponseDTO dto) {
		return User.builder().id(dto.getId()).username(dto.getUsername()).build();
	}
}
