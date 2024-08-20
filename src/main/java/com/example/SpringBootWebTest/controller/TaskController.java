package com.example.SpringBootWebTest.controller;

import com.example.SpringBootWebTest.dto.StrippedTaskResponseDTO;
import com.example.SpringBootWebTest.dto.TaskResponseDTO;
import com.example.SpringBootWebTest.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
	private final TaskService service;

	public TaskController(TaskService service) {
		this.service = service;
	}

	@GetMapping
	public TaskResponseDTO getTask(@RequestParam("id") Long id) {
		return this.service.findTaskById(id);
	}

	@PostMapping
	public TaskResponseDTO addTask(@RequestParam Long taskListId, @RequestBody TaskResponseDTO taskResponseDTO) {
		return this.service.addTaskToList(taskListId, taskResponseDTO);
	}

	@PostMapping("/sub-task")
	public TaskResponseDTO addSubTask(@RequestParam Long taskId, @RequestBody TaskResponseDTO taskResponseDTO) {
		return this.service.addSubTask(taskId, taskResponseDTO);
	}

	@PutMapping
	public TaskResponseDTO updateTask(@RequestBody TaskResponseDTO dto) {
		return this.service.updateTask(dto);
	}


	@DeleteMapping
	public TaskResponseDTO deleteTask(@RequestParam Long id) {
		return this.service.deleteTaskById(id);
	}

	@GetMapping("all")
	public List<TaskResponseDTO> getAllTask() {
		return this.service.findAllTasks();
	}

	@GetMapping("stripped")
	public StrippedTaskResponseDTO getStrippedTask(@RequestParam("id") Long id) {
		return this.service.getStrippedTaskById(id);
	}

	@GetMapping("stripped-all")
	public List<StrippedTaskResponseDTO> getAllStrippedTask() {
		return this.service.getAllStrippedTask();
	}

}
