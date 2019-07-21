package com.syf.proj.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 
 * @author sreekar
 *
 */
@RestControllerAdvice
public class CommonExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

	@ExceptionHandler(value = { CommonExcpetion.class })
	public ResponseEntity<CommonError> handleUnknownException(CommonExcpetion cex) {
		logger.error("CommonExceptionHandler --> {}", cex.getMessage());
		return ResponseEntity.status(cex.getStausCode()).body(cex.getError());
	}

}
