package com.springboot.jose.rest.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiError1 {

	
	private HttpStatus status;
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String debugMessage;
	
	private ApiError1() {
		timestamp = LocalDateTime.now();
	}

	ApiError1(HttpStatus status) {
		this();
		this.status = status;
	}

	ApiError1(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	ApiError1(HttpStatus status, String message) {
		this();
		this.status = status;
		this.message = message;
	}

	ApiError1(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}
	
}