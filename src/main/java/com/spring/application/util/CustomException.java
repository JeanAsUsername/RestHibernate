package com.spring.application.util;

public class CustomException {
	
	private String message;
	
	// __CONSTRUCTOR__
	
	public CustomException(String message) {
		this.message = message;
	}
	
	// Getters and Setters

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
