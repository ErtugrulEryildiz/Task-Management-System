package com.example.SpringBootWebTest.service;

import com.example.SpringBootWebTest.dto.StrippedUserResponseDTO;
import com.example.SpringBootWebTest.dto.UserRegistrationDTO;
import com.example.SpringBootWebTest.dto.UserResponseDTO;
import com.example.SpringBootWebTest.exception.InvalidUserCredentialsException;
import com.example.SpringBootWebTest.exception.ResourceNotFoundException;
import com.example.SpringBootWebTest.mapper.StrippedUserResponseMapper;
import com.example.SpringBootWebTest.mapper.UserResponseMapper;
import com.example.SpringBootWebTest.model.User;
import com.example.SpringBootWebTest.repository.UserRepo;
import com.example.SpringBootWebTest.security.PasswordHashing;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
	private final UserRepo repo;
	private final UserResponseMapper responseMapper;
	private final StrippedUserResponseMapper strippedUserResponseMapper;


	public UserService(
			UserRepo repo, UserResponseMapper responseMapper, StrippedUserResponseMapper strippedUserResponseMapper
	) {
		this.repo = repo;
		this.responseMapper = responseMapper;
		this.strippedUserResponseMapper = strippedUserResponseMapper;
	}

	public UserResponseDTO getUserById(Long id) throws ResourceNotFoundException {
		return this.repo.findById(id)
						.map(responseMapper::toDTO)
						.orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
	}

	public List<UserResponseDTO> findAll() {
		return this.repo.findAll().stream().map(responseMapper::toDTO).toList();
	}


	public UserResponseDTO updateUser(Long id, UserResponseDTO dto) throws ResourceNotFoundException {
		User user = this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));

		if (dto.getUsername() != null && !dto.getUsername().isEmpty()) user.setUsername(dto.getUsername());

		this.repo.save(user);
		return responseMapper.toDTO(user);
	}

	public UserResponseDTO deleteUser(Long id) throws ResourceNotFoundException {
		User user = this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		this.repo.deleteById(id);
		return responseMapper.toDTO(user);
	}

	public UserResponseDTO registerUser(UserRegistrationDTO dto) throws IllegalArgumentException {
		if (dto.getUsername() == null || dto.getUsername().isEmpty())
			throw new IllegalArgumentException("Username was not found in the payload.");
		if (dto.getPassword() == null || dto.getPassword().isEmpty())
			throw new IllegalArgumentException("Password was not found in the payload.");

		String hashedPassword = PasswordHashing.hashPassword(dto.getPassword());
		User user = User.builder().username(dto.getUsername()).password(hashedPassword).build();
		User savedUser = this.repo.save(user);

		return responseMapper.toDTO(savedUser);
	}

	public UserResponseDTO loginUser(UserRegistrationDTO dto) throws
															  ResourceNotFoundException,
															  InvalidUserCredentialsException {
		User user = this.repo.findByUsername(dto.getUsername())
							 .orElseThrow(() -> new ResourceNotFoundException("user", "username", dto.getUsername()));

		if (PasswordHashing.checkPassword(dto.getPassword(), user.getPassword())) {
			return responseMapper.toDTO(user);
		} else {
			throw new InvalidUserCredentialsException();
		}
	}

	public StrippedUserResponseDTO getStrippedUserById(Long id) {
		return this.repo.findById(id)
						.map(strippedUserResponseMapper::toDTO)
						.orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
	}

	public List<StrippedUserResponseDTO> getAllStrippedUser() {
		return this.repo.findAll().stream().map(strippedUserResponseMapper::toDTO).toList();
	}
}
