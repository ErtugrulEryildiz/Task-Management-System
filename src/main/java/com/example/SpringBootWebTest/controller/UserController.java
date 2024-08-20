package com.example.SpringBootWebTest.controller;

import com.example.SpringBootWebTest.dto.StrippedUserResponseDTO;
import com.example.SpringBootWebTest.dto.UserRegistrationDTO;
import com.example.SpringBootWebTest.dto.UserResponseDTO;
import com.example.SpringBootWebTest.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public UserResponseDTO getUser(@RequestParam Long id) {
		return this.userService.getUserById(id);
	}

	@PostMapping
	public UserResponseDTO registerUser(@RequestBody UserRegistrationDTO dto) {
		return this.userService.registerUser(dto);
	}

	@PutMapping
	public UserResponseDTO updateUser(@RequestParam Long id, @RequestBody UserResponseDTO userResponseDTO) {
		return this.userService.updateUser(id, userResponseDTO);
	}

	@DeleteMapping
	public UserResponseDTO deleteUser(@RequestParam Long id) {
		return this.userService.deleteUser(id);
	}

	@GetMapping("all")
	public List<UserResponseDTO> getUsers() {
		return this.userService.findAll();
	}

	@PostMapping("login")
	public UserResponseDTO loginUser(@RequestBody UserRegistrationDTO dto) {
		return this.userService.loginUser(dto);
	}

	@GetMapping("stripped")
	public StrippedUserResponseDTO getStrippedUser(@RequestParam Long id) {
		return this.userService.getStrippedUserById(id);
	}

	@GetMapping("stripped-all")
	public List<StrippedUserResponseDTO> getAllStrippedUsers() {
		return this.userService.getAllStrippedUser();
	}
}
