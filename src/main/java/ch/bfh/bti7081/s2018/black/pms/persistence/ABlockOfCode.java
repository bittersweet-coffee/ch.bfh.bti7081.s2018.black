package ch.bfh.bti7081.s2018.black.pms.persistence;

import javax.persistence.EntityManager;

/**
 * Source: http://www.copypasteisforword.com/notes/lambda-expressions-in-java
 * Author: enrique
 * Date:   16.05.2018
 */

/**
 * Interface ABlockOfCode
 * @param <T>: the interface ABlockOfCode must support any object type
 */
public interface ABlockOfCode<T> {
	
	  /**
	   * Each block of code will respond to this method
	   * @param em: an EntityManager from the EntityManagerFactory
	   * @return the result of the block of code that is passed by parameter
	   */
	  T execute(EntityManager em);
}