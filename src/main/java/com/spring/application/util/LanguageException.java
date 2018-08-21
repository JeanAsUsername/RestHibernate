package com.spring.application.util;

import com.spring.application.model.Language;

public class LanguageException extends Language {
	
	private CustomException exception;
	
	// Getters and Setters

	public CustomException getException() {
		return exception;
	}

	public void setException(String message) {
		this.exception = new CustomException(message);
	}

}
