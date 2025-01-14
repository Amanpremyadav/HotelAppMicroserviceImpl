package com.user.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.user.service.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse> handleUserNotFoundException(UserNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse(message, false, HttpStatus.NOT_FOUND);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	}
}
