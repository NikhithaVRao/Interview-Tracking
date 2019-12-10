package com.robosoft.interviewtracking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorData> handle(CustomException e){
		return new ResponseEntity<ErrorData>(new ErrorData(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
	}
}
