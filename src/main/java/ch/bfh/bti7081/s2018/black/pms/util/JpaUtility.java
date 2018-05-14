package ch.bfh.bti7081.s2018.black.pms.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * Inspired by https://bit.ly/2rDRJWo
 */
public class JpaUtility {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManagerFactory emFactory;
	
	/*static {
		// Singleton
		if (entityManagerFactory == null) {
			try {
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PMS");
			} catch (Exception e){
				throw e;
			}
		}
	
	}*/
	
	static {
		   emFactory = Persistence.createEntityManagerFactory("PMS");
	}
	public static EntityManager getEntityManager(){
		return emFactory.createEntityManager();
	}
	public static void close(){
		emFactory.close();
	}
	
	// TODO: Write generic method which accepts all kinds of EntityManager transactions

}
