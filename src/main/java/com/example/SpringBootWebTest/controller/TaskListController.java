package com.example.SpringBootWebTest.controller;

import com.example.SpringBootWebTest.dto.StrippedTaskListResponseDTO;
import com.example.SpringBootWebTest.dto.TaskListResponseDTO;
import com.example.SpringBootWebTest.service.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/task-list")
public class TaskListController {
	private final TaskListService taskListService;

	public TaskListController(TaskListService taskListService) {
		this.taskListService = taskListService;
	}

	@GetMapping
	public TaskListResponseDTO getTaskList(@RequestParam Long id) {
		return this.taskListService.getUserById(id);
	}

	@PostMapping
	public TaskListResponseDTO addTaskList(@RequestParam long userId, @RequestBody TaskListResponseDTO dto) {
		return this.taskListService.addTaskList(userId, dto);
	}

	@PutMapping
	public TaskListResponseDTO updateTaskList(@RequestParam Long id, @RequestBody TaskListResponseDTO dto) {
		return this.taskListService.updateTaskList(id, dto);
	}

	@DeleteMapping
	public TaskListResponseDTO deleteTaskList(@RequestParam Long id) {
		return this.taskListService.deleteTaskList(id);
	}

	@GetMapping("all")
	public List<TaskListResponseDTO> getTaskLists() {
		return this.taskListService.findAll();
	}

	@GetMapping("stripped")
	public StrippedTaskListResponseDTO getStrippedTaskList(@RequestParam Long id) {
		return this.taskListService.getStrippedTaskListById(id);
	}

	@GetMapping("stripped-all")
	public List<StrippedTaskListResponseDTO> getAllStrippedTaskList() {
		return this.taskListService.getAllStrippedTaskList();
	}
}
