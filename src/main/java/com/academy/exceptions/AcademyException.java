package com.academy.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class AcademyException extends RuntimeException {

	private final HttpStatus errorCode;

	public AcademyException(String msg, HttpStatus conflict) {
		super(msg);
		this.errorCode = conflict;
	}
}
