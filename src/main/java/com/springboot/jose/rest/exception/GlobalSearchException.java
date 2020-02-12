package com.springboot.jose.rest.exception;

public class GlobalSearchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GlobalSearchException(String message, Throwable cause) {
		super(message, cause);
	}

	public GlobalSearchException(Throwable cause) {
		super(cause);
	}
	
	public GlobalSearchException() {
		super("no results");
	}
	
	public GlobalSearchException(String message) {
		super(String.format("Search term %s no results", message));
	}

}
