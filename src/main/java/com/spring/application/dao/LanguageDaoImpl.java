package com.spring.application.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.spring.application.model.Language;

@Repository
public class LanguageDaoImpl implements ILanguageDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Language> findAllLanguages() {
		
		String hql = "from Language as lan ORDER BY lan.id";
		Query query = entityManager.createQuery(hql);

		List<Language> languages = (List<Language>) query.getResultList();
		return languages;
		
	}

	@Override
	public Language findLanguageById(Long id) throws Exception {
		
		String hql = "from Language where id=:id";
		Query query = entityManager.createQuery(hql).setParameter("id", id);
		Language language;
		
		try {
			
			language = (Language) query.getSingleResult(); // find the language
			
		} catch (Exception e) {
			
			// server error
			throw new Exception("Lenguaje no encontrado.");
		}
		
		// success
		return language;
		
	}

	@Override
	public Language findLanguageByName(String name) throws Exception {
		
		String hql = "from Language where name=:name";
		Query query = entityManager.createQuery(hql).setParameter("name", name);
		Language language;
		
		 try {
			 
			 language = (Language) query.getSingleResult();
			 
		 } catch (Exception e) {
			 
			 throw new Exception("Lenguaje no encontrado.");
			 
		 }
		 
		 // success
		 return language;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Language createLanguage(Language commingLanguage) throws Exception {
		
		String hql = "from Language";
		Query query = entityManager.createQuery(hql);

		List<Language> languages = (List<Language>) query.getResultList();
		
		// Verify the language don't already exists
		for (Language language:languages) {
			if ( language.getName().equalsIgnoreCase( commingLanguage.getName() ) ) {
				throw new Exception("Este lenguaje ya se encuentra registrado.");
			}
		}
		
		try {
			
			// save the language
			entityManager.persist(commingLanguage);
			
			// return the language
			return commingLanguage;
			
		} catch(Exception e) {
			throw new Exception("Error intentando crear este lenguaje.");
		}
		
	}

	@Override
	public Language removeLanguageById(Long id) throws Exception {
		
		String hql = "delete from Language where id=:id";
		Query query = entityManager.createQuery(hql).setParameter("id", id);
		
		try {
			
			// get the language before delete it
			Language languageToDelete = findLanguageById(id);
			
			// delete the language
			query.executeUpdate();
			
			// return the language
			return languageToDelete;
			
		} catch (Exception e) {
			throw new Exception("Error intentando eliminar este lenguaje."); // query fail
		}
	}

}
