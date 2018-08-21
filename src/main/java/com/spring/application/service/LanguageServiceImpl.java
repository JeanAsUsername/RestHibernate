package com.spring.application.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.application.dao.ILanguageDao;
import com.spring.application.model.Language;
import com.spring.application.util.LanguageException;

@Service("LanguageService")
@Transactional
public class LanguageServiceImpl implements ILanguageService {
	
	@Autowired
	private ILanguageDao languageDao;
	@Autowired
	private LanguageException languageException;

	@Override
	public List<Language> findAllLanguages() {
		
		return languageDao.findAllLanguages();
		
	}

	@Override
	public Language findLanguageById(Long id) {

		try {
			return languageDao.findLanguageById(id);
		} catch (Exception e) {
			languageException.setException(e.getMessage());
			return languageException;
		}
	}

	@Override
	public Language findLanguageByName(String name) {
		
		try {
			return languageDao.findLanguageByName(name);
		} catch (Exception e) {
			languageException.setException(e.getMessage());
			return languageException;
		}
		
	}

	@Override
	public Language createLanguage(Language commingLanguage) {
		
		try {
			return languageDao.createLanguage(commingLanguage);
		} catch (Exception e) {
			languageException.setException(e.getMessage());
			return languageException;
		}
		
	}

	@Override
	public Language removeLanguageById(Long id) {
		
		try {
			return languageDao.removeLanguageById(id);
		} catch (Exception e) {
			languageException.setException(e.getMessage());
			return languageException;
		}
		
	}
	


}
