package com.gayathri.tigercard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j2;
import reactor.core.Exceptions;

/**
 * Standardize exception handling for all the controllers, formatting response and returning the
 * correct HttpStatus code based on the exception type and/or some values from the exception object.
 */
@ControllerAdvice
@Log4j2
public class CommonExceptionHandler {
	  @ExceptionHandler(Exception.class)
	  public final ResponseEntity handleGeneralExceptions(final Exception ex) {
	    // Unwrap to get the original Exception/Throwable if it is a "wrapper" bubbled up through the
	    // reactor stack, other Exceptions.unwrap will return the original Exception being passed in.
	    final Throwable e = Exceptions.unwrap(ex);
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}
