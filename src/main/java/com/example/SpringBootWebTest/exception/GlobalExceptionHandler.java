package com.example.SpringBootWebTest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
		return new ResponseEntity<>(new ErrorDetails(exception.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidUserCredentialsException.class)
	public ResponseEntity<?> handleInvalidUserCredentialsException(InvalidUserCredentialsException exception) {
		return new ResponseEntity<>(new ErrorDetails(exception.getMessage()), HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleBaseException(Exception exception) {
		return new ResponseEntity<>(new ErrorDetails(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
