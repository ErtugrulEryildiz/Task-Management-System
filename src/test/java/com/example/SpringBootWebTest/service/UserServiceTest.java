package com.example.SpringBootWebTest.service;

import com.example.SpringBootWebTest.dto.UserRegistrationDTO;
import com.example.SpringBootWebTest.dto.UserResponseDTO;
import com.example.SpringBootWebTest.exception.InvalidUserCredentialsException;
import com.example.SpringBootWebTest.exception.ResourceNotFoundException;
import com.example.SpringBootWebTest.mapper.UserResponseMapper;
import com.example.SpringBootWebTest.model.User;
import com.example.SpringBootWebTest.repository.UserRepo;
import com.example.SpringBootWebTest.security.PasswordHashing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	private final String testUsername = "username";
	private final String testPassword = "password";
	private final String testHashedPassword = PasswordHashing.hashPassword(this.testPassword);
	private User testUser;
	private UserResponseDTO testUserResponseDTO;
	private UserRegistrationDTO testUserRegistrationDTO;

	@Mock
	private UserRepo userRepo;
	@Mock
	private UserResponseMapper responseMapper;
	@InjectMocks
	private UserService underTest;


	@BeforeEach
	void setUp() {
		User user = User.builder()
						.username(this.testUsername)
						.password(this.testHashedPassword)
						.taskLists(Collections.emptyList())
						.build();
		UserResponseDTO responseDTO = UserResponseDTO.builder()
													 .username(this.testUsername)
													 .taskLists(Collections.emptyList())
													 .build();
		UserRegistrationDTO registrationDTO = UserRegistrationDTO.builder()
																 .username(this.testUsername)
																 .password(this.testPassword)
																 .build();

		this.testUser = user;
		this.testUserRegistrationDTO = registrationDTO;
		this.testUserResponseDTO = responseDTO;

	}

	@Test
	void getUserById_ValidId_ReturnsDTO() {
		// Arrange
		when(this.userRepo.findById(this.testUser.getId())).thenReturn(Optional.of(this.testUser));
		when(responseMapper.toDTO(any(User.class))).thenReturn(this.testUserResponseDTO);
		// Act
		UserResponseDTO dto = underTest.getUserById(this.testUser.getId());
		// Assert
		assertThat(dto).isEqualTo(this.testUserResponseDTO);
	}

	@Test
	void getUserById_InvalidId_ThrowsResourceNotFoundException() {
		// Arrange
		Long invalidId = 1L;
		when(this.userRepo.findById(invalidId)).thenThrow(new ResourceNotFoundException("user", "id", invalidId));
		// Act & Assert
		assertThatThrownBy(() -> underTest.getUserById(invalidId)).isInstanceOf(ResourceNotFoundException.class)
																  .hasMessageContainingAll("user",
																						   "id",
																						   Long.toString(invalidId));
	}

	@Test
	void findAll_NoUserExists_ReturnsEmptyList() {
		// Arrange
		when(this.userRepo.findAll()).thenReturn(Collections.emptyList());
		// Act & Assert
		assertThat(underTest.findAll()).isEmpty();
	}

	@Test
	void findAll_SomeUserExist_ReturnsFilledList() {
		// Arrange
		when(this.userRepo.findAll()).thenReturn(List.of(User.builder().id(1L).username("user 1").build(),
														 User.builder().id(2L).username("user 2").build(),
														 User.builder().id(3L).username("user 3").build()));
		// Act & Assert
		assertThat(underTest.findAll()).isNotEmpty();
	}

	@Test
	void updateUser_UpdatesUsername_ReturnsUserResponseDTO() {
		// Arrange
		Long tempId = 1L;
		String tempUsername = "temp username";

		UserResponseDTO usernameUpdatePayload = UserResponseDTO.builder().username(tempUsername).build();
		UserResponseDTO expectedUserResponseDTO = UserResponseDTO.builder()
																 .id(testUserResponseDTO.getId())
																 .username(tempUsername)
																 .taskLists(testUserResponseDTO.getTaskLists())
																 .build();
		User expectedUser = User.builder()
								.id(testUser.getId())
								.username(tempUsername)
								.password(testUser.getPassword())
								.taskLists(testUser.getTaskLists())
								.build();

		when(this.userRepo.findById(tempId)).thenReturn(Optional.of(testUser));
		when(this.userRepo.save(any(User.class))).thenReturn(expectedUser);
		when(this.responseMapper.toDTO(testUser)).thenReturn(expectedUserResponseDTO);
		// Act
		UserResponseDTO updatedUserDTO = underTest.updateUser(tempId, usernameUpdatePayload);
		// Assert
		assertThat(updatedUserDTO).isEqualTo(expectedUserResponseDTO);
	}

	@Test
	void updateUser_UpdatesUsername_ThrowsResourceNotFoundException() {
		// Arrange
		long invalidId = 1L;
		String tempUsername = "temp username";
		UserResponseDTO usernameUpdatePayload = UserResponseDTO.builder().username(tempUsername).build();
		// Act & Assert
		assertThatThrownBy(() -> underTest.updateUser(invalidId, usernameUpdatePayload)).isInstanceOf(
				ResourceNotFoundException.class).hasMessageContainingAll("user", "id", Long.toString(invalidId));
	}

	@Test
	void updateUser_EmptyPayload_ReturnsUserResponseDTO() {
		// Arrange
		Long tempId = 1L;

		UserResponseDTO usernameUpdatePayload = UserResponseDTO.builder().build();
		UserResponseDTO expectedUserResponseDTO = UserResponseDTO.builder()
																 .id(testUserResponseDTO.getId())
																 .username(testUserResponseDTO.getUsername())
																 .taskLists(testUserResponseDTO.getTaskLists())
																 .build();
		User expectedUser = User.builder()
								.id(testUser.getId())
								.username(testUser.getUsername())
								.password(testUser.getPassword())
								.taskLists(testUser.getTaskLists())
								.build();

		when(this.userRepo.findById(tempId)).thenReturn(Optional.of(testUser));
		when(this.userRepo.save(any(User.class))).thenReturn(expectedUser);
		when(this.responseMapper.toDTO(testUser)).thenReturn(expectedUserResponseDTO);
		// Act
		UserResponseDTO updatedUserDTO = underTest.updateUser(tempId, usernameUpdatePayload);
		// Assert
		assertThat(updatedUserDTO).isEqualTo(expectedUserResponseDTO);
	}

	@Test
	void deleteUser_SuccessfulDelete_ReturnsUserDTO() {
		// Arrange
		long dummyId = 1L;
		when(userRepo.findById(dummyId)).thenReturn(Optional.of(this.testUser));
		when(responseMapper.toDTO(any(User.class))).thenReturn(testUserResponseDTO);
		// Act
		UserResponseDTO dto = underTest.deleteUser(dummyId);
		// Assert
		assertThat(dto).isEqualTo(testUserResponseDTO);
	}

	@Test
	void deleteUser_UserDoesNotExist_ThrowsResourceNotFoundException() {
		// Arrange
		long dummyId = 1L;
		when(userRepo.findById(dummyId)).thenThrow(new ResourceNotFoundException("user", "id", dummyId));
		// Act & Assert
		assertThatThrownBy(() -> underTest.deleteUser(dummyId)).isInstanceOf(ResourceNotFoundException.class)
															   .hasMessageContainingAll("user",
																						"id",
																						Long.toString(dummyId));
	}

	@Test
	void registerUser_SavesUser_ReturnsUserDTO() {
		// Arrange
		when(userRepo.save(any(User.class))).thenReturn(this.testUser);
		when(responseMapper.toDTO(any(User.class))).thenReturn(this.testUserResponseDTO);
		// Act
		UserResponseDTO savedUserDTO = underTest.registerUser(this.testUserRegistrationDTO);
		// Assert
		assertThat(savedUserDTO).isEqualTo(this.testUserResponseDTO);
	}

	@ParameterizedTest
	@CsvSource({
			"'', password", // No username
			"username, ''", // No password
	})
	void registerUser_NoUserName_ThrowsIllegalArgumentException(String username, String password) {
		// Arrange
		UserRegistrationDTO invalidDTO = UserRegistrationDTO.builder().username(username).password(password).build();
		// Act & Assert
		assertThatThrownBy(() -> underTest.registerUser(invalidDTO)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void loginUser_SuccessfulLogin_ReturnsTrue() {
		// Arrange
		when(userRepo.findByUsername(this.testUsername)).thenReturn(Optional.of(this.testUser));
		when(responseMapper.toDTO(any(User.class))).thenReturn(this.testUserResponseDTO);
		// Act
		UserResponseDTO dto = underTest.loginUser(this.testUserRegistrationDTO);
		// Assert
		assertThat(dto).isEqualTo(this.testUserResponseDTO);
	}

	@Test
	void loginUser_UnsuccessfulLogin_ThrowsResourceNotFoundException() {
		// Arrange
		when(userRepo.findByUsername(anyString())).thenThrow(new ResourceNotFoundException("user",
																						   "username",
																						   this.testUsername));
		// Act & Assert
		assertThatThrownBy(() -> underTest.loginUser(this.testUserRegistrationDTO)).isInstanceOf(
				ResourceNotFoundException.class).hasMessageContainingAll("user", "username", this.testUsername);
	}

	@Test
	void loginUser_UnsuccessfulLogin_ThrowsInvalidUserCredentialsException() {
		// Arrange
		String invalidPassword = "invalid password";
		UserRegistrationDTO dto = UserRegistrationDTO.builder()
													 .username(this.testUsername)
													 .password(invalidPassword)
													 .build();
		when(userRepo.findByUsername(this.testUsername)).thenReturn(Optional.of(this.testUser));
		// Act & Assert
		assertThatThrownBy(() -> underTest.loginUser(dto)).isInstanceOf(InvalidUserCredentialsException.class);
	}
}