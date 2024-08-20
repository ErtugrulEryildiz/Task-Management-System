package com.example.SpringBootWebTest.repository;

import com.example.SpringBootWebTest.exception.ResourceNotFoundException;
import com.example.SpringBootWebTest.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class UserRepoTest {

	@Autowired
	private UserRepo underTest;

	@AfterEach
	void removeAll() {
		underTest.deleteAll();
	}

	@Test
	void shouldFindUserByUsername() {
		User user = User.builder().username("user").password("password").build();

		underTest.save(user);

		User fetchedUser = underTest.findByUsername(user.getUsername())
									.orElseThrow(() -> new ResourceNotFoundException("user",
																					 "username",
																					 user.getUsername()));

		assertThat(fetchedUser).isEqualTo(user);
	}
}