package com.gayathri.tigercard.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TigerCardErrorCode {
	INTERNAL_ERROR(2000, "Internal Error", HttpStatus.INTERNAL_SERVER_ERROR),
	DUPLICATE_INPUT(2003, "Invalid inputs, Fare Cofiguration for the provided data or time rage or Zone config already exist.", HttpStatus.BAD_REQUEST);

	@Getter
	private final int code;
	@Getter
	private final String message;
	@Getter
	private final HttpStatus httpStatusCode;
}
