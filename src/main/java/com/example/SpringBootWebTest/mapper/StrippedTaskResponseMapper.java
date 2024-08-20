package com.example.SpringBootWebTest.mapper;

import com.example.SpringBootWebTest.dto.StrippedTaskResponseDTO;
import com.example.SpringBootWebTest.model.Task;
import org.springframework.stereotype.Component;


@Component
public class StrippedTaskResponseMapper implements EntityDTOMapper<Task, StrippedTaskResponseDTO> {
	@Override
	public StrippedTaskResponseDTO toDTO(Task task) {
		return StrippedTaskResponseDTO.builder()
									  .id(task.getId())
									  .name(task.getName())
									  .completed(task.isCompleted())
									  .description(task.getDescription())
									  .build();
	}

	@Override
	public Task toEntity(StrippedTaskResponseDTO dto) {
		return Task.builder()
				   .id(dto.getId())
				   .name(dto.getName())
				   .completed(dto.getCompleted())
				   .description(dto.getDescription())
				   .build();
	}
}
