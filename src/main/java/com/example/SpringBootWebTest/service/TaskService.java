package com.example.SpringBootWebTest.service;

import com.example.SpringBootWebTest.dto.StrippedTaskResponseDTO;
import com.example.SpringBootWebTest.dto.TaskResponseDTO;
import com.example.SpringBootWebTest.exception.ResourceNotFoundException;
import com.example.SpringBootWebTest.mapper.StrippedTaskResponseMapper;
import com.example.SpringBootWebTest.mapper.TaskResponseMapper;
import com.example.SpringBootWebTest.model.Task;
import com.example.SpringBootWebTest.model.TaskList;
import com.example.SpringBootWebTest.repository.TaskListRepo;
import com.example.SpringBootWebTest.repository.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {
	private final TaskRepo taskRepo;
	private final TaskListRepo taskListRepo;
	private final TaskResponseMapper taskResponseMapper;
	private final StrippedTaskResponseMapper strippedTaskResponseMapper;

	public TaskService(
			TaskRepo taskRepo,
			TaskListRepo taskListRepo,
			TaskResponseMapper taskResponseMapper,
			StrippedTaskResponseMapper strippedTaskResponseMapper
	) {
		this.taskRepo = taskRepo;
		this.taskListRepo = taskListRepo;
		this.taskResponseMapper = taskResponseMapper;
		this.strippedTaskResponseMapper = strippedTaskResponseMapper;
	}

	public TaskResponseDTO addTaskToList(Long id, TaskResponseDTO dto) throws ResourceNotFoundException {
		TaskList taskList = taskListRepo.findById(id)
										.orElseThrow(() -> new ResourceNotFoundException("task-list", "id", id));
		Task task = Task.builder()
						.name(dto.getName())
						.description(dto.getDescription())
						.completed(dto.getCompleted())
						.taskList(taskList)
						.build();

		return taskResponseMapper.toDTO(taskRepo.save(task));
	}

	public TaskResponseDTO addSubTask(Long id, TaskResponseDTO dto) throws ResourceNotFoundException {
		Task parentTask = taskRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("task", "id", id));
		Task task = Task.builder()
						.name(dto.getName())
						.description(dto.getDescription())
						.completed(dto.getCompleted())
						.taskList(parentTask.getTaskList())
						.parentTask(parentTask)
						.build();

		return taskResponseMapper.toDTO(taskRepo.save(task));
	}

	public List<TaskResponseDTO> findAllTasks() {
		return this.taskRepo.findAll().stream().map(taskResponseMapper::toDTO).toList();
	}

	public TaskResponseDTO findTaskById(Long id) throws ResourceNotFoundException {
		return this.taskRepo.findById(id)
							.map(taskResponseMapper::toDTO)
							.orElseThrow(() -> new ResourceNotFoundException("task", "id", id));
	}

	public TaskResponseDTO deleteTaskById(Long id) throws ResourceNotFoundException {
		Task task = this.taskRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("task", "id", id));
		this.taskRepo.deleteById(id);

		return taskResponseMapper.toDTO(task);
	}

	public TaskResponseDTO updateTask(TaskResponseDTO dto) throws ResourceNotFoundException {
		Task task = taskRepo.findById(dto.getId())
							.orElseThrow(() -> new ResourceNotFoundException("task", "id", dto.getId()));

		if (dto.getName() != null) task.setName(dto.getName());
		if (dto.getDescription() != null) task.setDescription(dto.getDescription());
		if (dto.getCompleted() != null) task.setCompleted(dto.getCompleted());

		return taskResponseMapper.toDTO(taskRepo.save(task));
	}

	public StrippedTaskResponseDTO getStrippedTaskById(Long id) {
		return this.taskRepo.findById(id)
							.map(strippedTaskResponseMapper::toDTO)
							.orElseThrow(() -> new ResourceNotFoundException("task", "id", id));
	}

	public List<StrippedTaskResponseDTO> getAllStrippedTask() {
		return this.taskRepo.findAll().stream().map(strippedTaskResponseMapper::toDTO).toList();
	}
}
