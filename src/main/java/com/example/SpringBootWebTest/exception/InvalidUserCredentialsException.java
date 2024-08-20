package com.example.SpringBootWebTest.exception;

import lombok.Getter;


@Getter
public class InvalidUserCredentialsException extends RuntimeException {
	public InvalidUserCredentialsException() {
		super("Invalid user credentials provided.");
	}
}
