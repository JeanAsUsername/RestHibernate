package com.spring.application.dao;

import java.util.List;

import com.spring.application.model.Language;

public interface ILanguageDao {
	
	List<Language> findAllLanguages();
	
	Language findLanguageById(Long id) throws Exception;
	
	Language findLanguageByName(String name) throws Exception;
	
	Language createLanguage(Language commingLanguage) throws Exception;
	
	Language removeLanguageById(Long id) throws Exception;

}
