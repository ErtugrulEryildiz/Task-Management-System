package com.example.SpringBootWebTest.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@Getter
@NoArgsConstructor
public class ErrorDetails {
	private Date date = new Date();
	private String message;
	private String details;

	public ErrorDetails(String message) {
		this.date = new Date();
		this.message = message;
	}

	public ErrorDetails(String message, String details) {
		this.date = new Date();
		this.message = message;
		this.details = details;
	}
}