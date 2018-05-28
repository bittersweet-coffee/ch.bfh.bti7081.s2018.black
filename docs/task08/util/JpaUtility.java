package ch.bfh.bti7081.s2018.black.pms.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/**
 * class JpaUtility
 * @author musaa1, karras
 * @version 0.2
 * Inspired by https://bit.ly/2rDRJWo
 */

public class JpaUtility {
	
	// EntityManagerFactory of the application
	private static EntityManagerFactory emf;
	
	/**
	 * initialize the EntityManagerFactory of the application
	 */
	static {
		// Singleton
		if (emf == null) {
			try {
				emf = Persistence.createEntityManagerFactory("PMS");
			} catch (Exception e){
				throw e;
			}
		}
	}
	
	/**
	 * create a new EntityManager
	 * @return a new EntityManager from the EntityManagerFactory
	 */
	public static EntityManager createEntityManager(){
		return emf.createEntityManager();
	}
	

	/**
	 * Source: http://www.copypasteisforword.com/notes/lambda-expressions-in-java
	 * Author: enrique
	 * Date: 16.05.2018
	 * 
	 * generic execute method that will be executed in the class JpaDataAccesObject
	 * @param aBlockOfCode: a block of code that shall be executed between begin 
	 * 						and commit of the transaction
	 * @return the result of the block of code that is passed by parameter
	 */
	public <T> T execute(ABlockOfCode<T> aBlockOfCode) {
		EntityManager entityManager = JpaUtility.createEntityManager();	
		EntityTransaction transaction = null; // transaction is null in the beginning
		try {
			transaction = entityManager.getTransaction();
			// begin the transaction to our database
			transaction.begin();
			// variable of the result of the execute method with our 
			// block of code that we pass in the class JpaDataAccessObject
			// here we can save, remove or update objects in the database
			T returnValue = aBlockOfCode.execute(entityManager);
			// After the operation on the database, we commit the changes
			transaction.commit();
			// return the result of the executed method
			return returnValue;
		} catch (Exception e) {
			// if the transaction is still null, then do a rollback of the transaction
			if (transaction != null) {
				transaction.rollback();
			}
		    throw new RuntimeException(e);
		} finally {
			// the entityManager will be closed at the end
			entityManager.close();
		}
	}
}
