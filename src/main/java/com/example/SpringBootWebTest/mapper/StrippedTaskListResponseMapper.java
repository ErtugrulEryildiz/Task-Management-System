package com.example.SpringBootWebTest.mapper;

import com.example.SpringBootWebTest.dto.StrippedTaskListResponseDTO;
import com.example.SpringBootWebTest.model.TaskList;
import org.springframework.stereotype.Component;


@Component
public class StrippedTaskListResponseMapper implements EntityDTOMapper<TaskList, StrippedTaskListResponseDTO> {
	private final TaskListResponseMapper mapper = new TaskListResponseMapper();

	@Override
	public StrippedTaskListResponseDTO toDTO(TaskList taskList) {
		return StrippedTaskListResponseDTO.builder()
										  .id(taskList.getId())
										  .name(taskList.getName())
										  .description(taskList.getDescription())
										  .build();
	}

	@Override
	public TaskList toEntity(StrippedTaskListResponseDTO dto) {
		return TaskList.builder().id(dto.getId()).name(dto.getName()).description(dto.getDescription()).build();
	}
}
