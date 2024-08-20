package com.example.SpringBootWebTest.repository;

import com.example.SpringBootWebTest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	@Query("SELECT user " + "FROM User user " + "WHERE user.username = :username")
	Optional<User> findByUsername(String username);
}
