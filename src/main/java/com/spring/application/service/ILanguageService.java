package com.spring.application.service;

import java.util.List;

import com.spring.application.model.Language;

public interface ILanguageService {

	List<Language> findAllLanguages();
	
	Language findLanguageById(Long id);
	
	Language findLanguageByName(String name);
	
	Language createLanguage(Language commingLanguage);
	
	Language removeLanguageById(Long id);
	
}
