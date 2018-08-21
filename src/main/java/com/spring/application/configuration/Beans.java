package com.spring.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.spring.application.util.LanguageException;

@Configuration
public class Beans {
	
	@Bean
	@Scope("prototype")
	public LanguageException LanguageException() {
		return new LanguageException();
	}
	
}
