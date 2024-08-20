package com.example.SpringBootWebTest.service;


import com.example.SpringBootWebTest.dto.StrippedTaskListResponseDTO;
import com.example.SpringBootWebTest.dto.TaskListResponseDTO;
import com.example.SpringBootWebTest.exception.ResourceNotFoundException;
import com.example.SpringBootWebTest.mapper.StrippedTaskListResponseMapper;
import com.example.SpringBootWebTest.mapper.TaskListResponseMapper;
import com.example.SpringBootWebTest.model.TaskList;
import com.example.SpringBootWebTest.model.User;
import com.example.SpringBootWebTest.repository.TaskListRepo;
import com.example.SpringBootWebTest.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskListService {
	private final TaskListRepo taskListRepo;
	private final UserRepo userRepo;
	private final TaskListResponseMapper taskListResponseMapper;
	private final StrippedTaskListResponseMapper strippedTaskListResponseMapper;

	public TaskListService(
			TaskListRepo taskListRepo,
			UserRepo userRepo,
			TaskListResponseMapper taskListResponseMapper,
			StrippedTaskListResponseMapper strippedTaskListResponseMapper
	) {
		this.taskListRepo = taskListRepo;
		this.userRepo = userRepo;
		this.taskListResponseMapper = taskListResponseMapper;
		this.strippedTaskListResponseMapper = strippedTaskListResponseMapper;

	}

	public TaskListResponseDTO getUserById(Long id) throws ResourceNotFoundException {
		return this.taskListRepo.findById(id)
								.map(taskListResponseMapper::toDTO)
								.orElseThrow(() -> new ResourceNotFoundException("task-list", "id", id));
	}

	public TaskListResponseDTO addTaskList(Long userId, TaskListResponseDTO dto) {
		TaskList taskList = taskListResponseMapper.toEntity(dto);
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		taskList.setUser(user);

		this.taskListRepo.save(taskList);

		return taskListResponseMapper.toDTO(taskList);
	}

	public List<TaskListResponseDTO> findAll() {
		return this.taskListRepo.findAll().stream().map(taskListResponseMapper::toDTO).toList();
	}

	public TaskListResponseDTO updateTaskList(Long id, TaskListResponseDTO dto) throws ResourceNotFoundException {
		TaskList taskList = this.taskListRepo.findById(id)
											 .orElseThrow(() -> new ResourceNotFoundException("task-list", "id", id));

		if (dto.getName() != null) taskList.setName(dto.getName());
		if (dto.getDescription() != null) taskList.setDescription(dto.getDescription());

		return taskListResponseMapper.toDTO(this.taskListRepo.save(taskList));
	}

	public TaskListResponseDTO deleteTaskList(Long id) throws ResourceNotFoundException {
		TaskList taskList = taskListRepo.findById(id)
										.orElseThrow(() -> new ResourceNotFoundException("task-list", "id", id));
		taskListRepo.deleteById(id);

		return taskListResponseMapper.toDTO(taskList);
	}

	public StrippedTaskListResponseDTO getStrippedTaskListById(Long id) {
		return this.taskListRepo.findById(id)
								.map(strippedTaskListResponseMapper::toDTO)
								.orElseThrow(() -> new ResourceNotFoundException("task-list", "id", id));
	}

	public List<StrippedTaskListResponseDTO> getAllStrippedTaskList() {
		return this.taskListRepo.findAll().stream().map(strippedTaskListResponseMapper::toDTO).toList();
	}
}