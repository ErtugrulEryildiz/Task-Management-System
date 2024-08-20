package com.example.SpringBootWebTest.mapper;

import com.example.SpringBootWebTest.dto.UserResponseDTO;
import com.example.SpringBootWebTest.model.User;
import org.springframework.stereotype.Component;


@Component
public class UserResponseMapper implements EntityDTOMapper<User, UserResponseDTO> {
	private final TaskListResponseMapper mapper = new TaskListResponseMapper();

	@Override
	public UserResponseDTO toDTO(User user) {
		return UserResponseDTO.builder()
							  .id(user.getId())
							  .username(user.getUsername())
							  .taskLists(user.getTaskLists() != null
												 ? user.getTaskLists().stream().map(mapper::toDTO).toList()
												 : null)
							  .build();
	}

	@Override
	public User toEntity(UserResponseDTO dto) {
		return User.builder()
				   .id(dto.getId())
				   .username(dto.getUsername())
				   .taskLists(dto.getTaskLists() != null
									  ? dto.getTaskLists().stream().map(mapper::toEntity).toList()
									  : null)
				   .build();
	}
}
