package com.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@RestControllerAdvice
public class HandleException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<errorMsg> exceptionHandle(WebRequest rq, Exception ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new errorMsg("400", ex.getMessage()));
	}

	// validation exception fail
	@ExceptionHandler(BindException.class)
	public ResponseEntity<errorMsg> validateEx(BindException ex, WebRequest rq) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new errorMsg("400", ex.getAllErrors().get(0).getDefaultMessage()));
	}

	// invalid format
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<errorMsg> validateEx(HttpMessageNotReadableException ex, WebRequest rq) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new errorMsg("400", "invalid value"));
	}
}

@Setter
@Getter
@AllArgsConstructor
class errorMsg {
	private String status;

	private String errorMsg;
}