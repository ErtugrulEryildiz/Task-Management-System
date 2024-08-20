package com.example.SpringBootWebTest.mapper;

import com.example.SpringBootWebTest.dto.TaskListResponseDTO;
import com.example.SpringBootWebTest.model.TaskList;
import org.springframework.stereotype.Component;


@Component
public class TaskListResponseMapper implements EntityDTOMapper<TaskList, TaskListResponseDTO> {
	private final TaskResponseMapper mapper = new TaskResponseMapper();

	@Override
	public TaskListResponseDTO toDTO(TaskList taskList) {
		return TaskListResponseDTO.builder()
								  .id(taskList.getId())
								  .name(taskList.getName())
								  .description(taskList.getDescription())
								  .tasks(taskList.getTasks() != null
												 ? taskList.getTasks().stream().map(mapper::toDTO).toList()
												 : null)
								  .build();
	}

	@Override
	public TaskList toEntity(TaskListResponseDTO dto) {
		return TaskList.builder()
					   .id(dto.getId())
					   .name(dto.getName())
					   .description(dto.getDescription())
					   .tasks(dto.getTasks() != null
									  ? dto.getTasks().stream().map(mapper::toEntity).toList()
									  : null)
					   .build();
	}
}


