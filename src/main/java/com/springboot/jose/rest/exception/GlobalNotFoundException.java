package com.springboot.jose.rest.exception;

public class GlobalNotFoundException extends RuntimeException  {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GlobalNotFoundException(String message) {
		super(message);
	}

	public GlobalNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public GlobalNotFoundException(Throwable cause) {
		super(cause);
	}

	public GlobalNotFoundException(Long id) {
		super("Not Found " + id);
	}
}
