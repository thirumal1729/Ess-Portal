package com.ty.ess.portal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ty.ess.portal.dto.ResponseStructure;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ResponseStructure<String>> catchValidationException(ValidationException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		responseStructure.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(exception.getMessage())
				.data("Please Enter the valid details").build();
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> catchUserNotFoundException(UserNotFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		responseStructure.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(exception.getMessage())
				.data("Please Enter ").build();
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ResponseStructure<String>> catchBadCredentialsException(BadCredentialsException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.builder().statusCode(HttpStatus.FORBIDDEN.value()).message(exception.getMessage()).data("Credentials Invalid !!").build();
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(EmployeeLeaveRequestEmptyException.class)
	public ResponseEntity<ResponseStructure<String>> catchEmployeeLeaveRequestEmptyException(EmployeeLeaveRequestEmptyException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.builder().statusCode(HttpStatus.NO_CONTENT.value()).message(exception.getMessage()).data("NO CONTENT").build();
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
	}

}
