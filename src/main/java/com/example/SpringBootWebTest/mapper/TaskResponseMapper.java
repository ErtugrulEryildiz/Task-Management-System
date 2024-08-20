package com.example.SpringBootWebTest.mapper;

import com.example.SpringBootWebTest.dto.TaskResponseDTO;
import com.example.SpringBootWebTest.model.Task;
import org.springframework.stereotype.Component;


@Component
public class TaskResponseMapper implements EntityDTOMapper<Task, TaskResponseDTO> {
	@Override
	public TaskResponseDTO toDTO(Task task) {
		return TaskResponseDTO.builder()
							  .id(task.getId())
							  .name(task.getName())
							  .description(task.getDescription())
							  .completed(task.isCompleted())
							  .children(task.getChildrenTask() != null
												? task.getChildrenTask().stream().map(this::toDTO).toList()
												: null)
							  .build();
	}

	@Override
	public Task toEntity(TaskResponseDTO dto) {
		return Task.builder()
				   .id(dto.getId())
				   .name(dto.getName())
				   .description(dto.getDescription())
				   .completed(dto.getCompleted())
				   .childrenTask(dto.getChildren() != null
										 ? dto.getChildren().stream().map(this::toEntity).toList()
										 : null)
				   .build();
	}
}
