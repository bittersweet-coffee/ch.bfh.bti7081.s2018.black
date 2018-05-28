package ch.bfh.bti7081.s2018.black.pms.util;

import javax.persistence.EntityManager;

/**
 * interface ABlockOfCode
 * @author musaa1
 * @version 0.1
 * @param <T>: the interface ABlockOfCode must support any object type
 */

//Source: http://www.copypasteisforword.com/notes/lambda-expressions-in-java
//Author: enrique
//Date: 16.05.2018
public interface ABlockOfCode<T> {
	
	  /**
	   * each block of code will respond to this method
	   * @param em: an EntityManager from the EntityManagerFactory
	   * @return the result of the block of code that is passed by parameter
	   */
	  T execute(EntityManager em);
}