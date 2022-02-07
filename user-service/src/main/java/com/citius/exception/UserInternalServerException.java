package com.citius.exception;

public class UserInternalServerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserInternalServerException(String str) {
		super(str);
	}

}
