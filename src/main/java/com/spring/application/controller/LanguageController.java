package com.spring.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.application.model.Language;
import com.spring.application.service.ILanguageService;
import com.spring.application.util.LanguageException;

@RestController
@RequestMapping(value="/v1")
public class LanguageController {
	
	/*
	 * 	__ROUTES__
	 * 
	 *  GET - /languages
	 *  POST - /languages - body -> {"name":"languageName"}
	 *  GET - /languages/findById/{languageId}
	 * 	GET - /languages/findByName/{languageName}
	 * 	DELETE - /languages/{languageId}
	 * 
	 * */
	
	@Autowired
	private ILanguageService languageService;
	@Autowired
	LanguageException languageException;
	
	// GET
	@RequestMapping(value="/languages", method=RequestMethod.GET)
	public ResponseEntity<List<Language>> findAllLanguages() {
		
		List<Language> languages = languageService.findAllLanguages();
		return new ResponseEntity<List<Language>>(languages, HttpStatus.OK);
	}
	
	// GET
	@RequestMapping(value="/languages/findById/{id}", method=RequestMethod.GET)
	public ResponseEntity<Language> findLanguageById(@PathVariable("id") String commingId) {
		
		Long id;
		HttpStatus status;
		
		// validate
		
		try {
			id = Long.parseLong(commingId);
		} catch(Exception e) {
			
			status = HttpStatus.BAD_REQUEST;
			languageException.setException("ID invalida.");;
			return new ResponseEntity<Language>(languageException, status);
			
		}
		
		// continue
		
		Language language = languageService.findLanguageById(id);
		
		if (language instanceof LanguageException) {
			status = HttpStatus.NO_CONTENT;
		}
		
		status = HttpStatus.OK;
		
		return new ResponseEntity<Language>(language,status);
	}
	
	// GET
	@RequestMapping(value="/languages/findByName/{name}", method=RequestMethod.GET)
	public ResponseEntity<Language> findLanguageByName(@PathVariable("name") String name) {
		
		HttpStatus status;
		
		Language language = languageService.findLanguageByName(name);
		
		if (language instanceof LanguageException) {
			status = HttpStatus.NO_CONTENT;
		}
		
		status = HttpStatus.OK;
		
		return new ResponseEntity<Language>(language,status);
	}
	
	// POST
	@RequestMapping(value="/languages", method=RequestMethod.POST)
	public ResponseEntity<Language> createLanguage(@RequestBody Language commingLanguage) {
		
		HttpStatus status;
		
		Language newLanguage = languageService.createLanguage(commingLanguage);
	
		
		if ( !(newLanguage instanceof Language) ) {
			
			status = HttpStatus.CONFLICT;
			languageException.setException("Error intentando crear este lenguaje.");
			
			return new ResponseEntity<Language>(languageException, status);
		}
		
		// success
		status = HttpStatus.CREATED;
		
		return new ResponseEntity<Language>(newLanguage, status);
		
	}
	
	// DELETE
	@RequestMapping(value="/languages/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> removeLanguage(@PathVariable("id") String commingId) {
		
		HttpStatus status;
		Long id;
		Language languageToDelete;
		
		// validate
		
		try {
			id = Long.parseLong(commingId);
		} catch(Exception e) {
			
			status = HttpStatus.BAD_REQUEST;
			languageException.setException("ID invalida.");;
			return new ResponseEntity<Language>(languageException, status);
		}
		
		// continue
		
		languageToDelete = languageService.removeLanguageById(id);
		
		if ( !(languageToDelete instanceof Language) ) {
			
			status = HttpStatus.CONFLICT;
			languageException.setException("Error intentando eliminar este lenguaje.");
			
			return new ResponseEntity<Language>(languageException, status);
		}


		status = HttpStatus.OK;
		
		return new ResponseEntity<Language>(languageToDelete, status);
		
	}

}
