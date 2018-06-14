package ch.bfh.bti7081.s2018.black.pms.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;


/**
 * class JpaUtility
 * @author musaa1, karras
 * @version 0.3
 * Inspired by https://bit.ly/2rDRJWo
 */

public class JpaUtility {
	
	// EntityManagerFactory of the application
	private static EntityManagerFactory emf;
	// EntityManager of the application
	private static EntityManager em;

	final static Logger logger = Logger.getLogger(JpaUtility.class);
	
	/**
	 * initialize the EntityManagerFactory and EntityManager of the application
	 */
	static {
		// Singleton
		if (emf == null) {
			try {
				logger.info("Initializing EntityManagerFactory");
				emf = Persistence.createEntityManagerFactory("PMS");
				em = emf.createEntityManager();
			} catch (Exception e){
				logger.error(e);
				throw e;
			}
		}
	}
	
	/**
	 * getter of the EntityManager
	 * @return the static EntityManager
	 */
	public static EntityManager getEntityManager(){
		return em;
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
		EntityManager entityManager = JpaUtility.getEntityManager();
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
			logger.error(e);
			// if the transaction is still null, then do a rollback of the transaction
			if (transaction != null) {
				transaction.rollback();
			}
		    throw new RuntimeException(e);
		}
	}
}
