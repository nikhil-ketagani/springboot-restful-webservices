package com.nikhil.springboot.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ResourceNotFoundException.class)
	 public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
			 WebRequest webRequest){
		 ErrorDetails errorDetails = ErrorDetails.builder()
		 .timestamp(LocalDateTime.now())
		 .message(exception.getMessage())
		 .path(webRequest.getDescription(false))
		 .errorCode("USER_NOT_FOUND").build();
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		 
	 }
	
	@ExceptionHandler(EmailAlreadyExistException.class)
	 public ResponseEntity<ErrorDetails> handleEmailAlreadyExistException(EmailAlreadyExistException exception,
			 WebRequest webRequest){
		 ErrorDetails errorDetails = ErrorDetails.builder()
		 .timestamp(LocalDateTime.now())
		 .message(exception.getMessage())
		 .path(webRequest.getDescription(false))
		 .errorCode("EMAIL_ALREADY_EXISTS").build();
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
		 
	 }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest){
		ErrorDetails errorDetails = ErrorDetails.builder()
				 .timestamp(LocalDateTime.now())
				 .message(exception.getMessage())
				 .path(webRequest.getDescription(false))
				 .errorCode("INTERNLA_SERVER_ERROR").build();
				return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		HashMap<String, String> errors= new HashMap<>();
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		allErrors.forEach(error->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		ErrorDetails errorDetails = ErrorDetails.builder()
				 .timestamp(LocalDateTime.now())
				 .message(errors.toString())
				 .path(request.getDescription(false))
				 .errorCode("VALIDATION_ERROR").build();
				return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
