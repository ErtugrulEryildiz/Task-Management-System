package com.example.SpringBootWebTest.service;

import com.example.SpringBootWebTest.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepo userRepo;
	private UserService underTest;


	@BeforeEach
	void setUp() {
	}

	@Test
	@Disabled
	void shouldGetUserById() {
	}

	@Test
	@Disabled
	void findAll() {
	}

	@Test
	@Disabled
	void updateUser() {
	}

	@Test
	@Disabled
	void deleteUser() {
	}

	@Test
	void registerUser() {
		// Arrange

		// Act
		// Assert
	}

	@Test
	@Disabled
	void loginUser() {
	}
}