package com.neoquant.productcrud.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.neoquant.productcrud.exception.ProductNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception){
		Map<String, String> errorMap = new HashMap<String, String>();
		exception.getBindingResult().getFieldErrors().forEach(error->{
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ProductNotFoundException.class)
	public Map<String, String> handleBusinessException(ProductNotFoundException exception){
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage", exception.getMessage());
		return errorMap;
	}
}
