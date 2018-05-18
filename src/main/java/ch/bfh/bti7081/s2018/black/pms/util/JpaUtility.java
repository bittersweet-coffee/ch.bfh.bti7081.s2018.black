package ch.bfh.bti7081.s2018.black.pms.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ch.bfh.bti7081.s2018.black.pms.model.UserModel;

/*
 * Inspired by https://bit.ly/2rDRJWo
 */
public class JpaUtility {
	
	private static EntityManagerFactory entityManagerFactory;
	
	static {
		// Singleton
		if (entityManagerFactory == null) {
			try {
				entityManagerFactory = Persistence.createEntityManagerFactory("PMS");
			} catch (Exception e){
				throw e;
			}
		}
	}
	
	public static EntityManager getEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
	
	public static void close(){
		entityManagerFactory.close();
	}
	
	// TODO: Write generic method which accepts all kinds of EntityManager transactions
	// Source: http://www.copypasteisforword.com/notes/lambda-expressions-in-java
	// Author: enrique
	// Date: 16.05.2018
	public <T> T execute(ABlockOfCode<T> aBlockOfCode) {
		EntityManager entityManager = JpaUtility.getEntityManager();	
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			T returnValue = aBlockOfCode.execute(entityManager);
			transaction.commit();
			return returnValue;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		    throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}
	
	public static <T> void remove(T object) {
		EntityManager entityManager = JpaUtility.getEntityManager();	
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.remove(object);
			transaction.commit();
		
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			entityManager.close();
		}
	}
}
