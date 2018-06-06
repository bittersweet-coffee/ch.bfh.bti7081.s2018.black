package ch.bfh.bti7081.s2018.black.pms.persistence;

import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.EntityModel;

/**
 * class JpaDataAccessObject
 * @author musaa1
 * @version 0.1
 * this class is needed from the presenter classes to access the objects 
 * in the database
 */

// Source: http://www.copypasteisforword.com/notes/lambda-expressions-in-java
// Author: enrique
// Date: 16.05.2018
public class JpaDataAccessObject {
	
	// JPA transaction variable
	private JpaUtility transaction;
	private int lastId;
	 
	/**
	 * creates a new object with a transaction
	 * @param transaction: the transaction object that will be used
	 */
	public JpaDataAccessObject(JpaUtility transaction) {
		this.transaction = transaction;
	}
	
	/**
	 * anonymous class to store an entity in the database
	 * @param entity: entity that will be stored in the database
	 */
	public void store(EntityModel entity) {
		// execute method of the JpaUtility class with our block of code
		transaction.execute(
				// lambda for writing the anonymous class
				(entityManager) -> { 
					// the object will be stored in the database
					entityManager.persist(entity);
					entityManager.flush();
					lastId = entity.getId();
					// the method does not return an object
					return null;
				}
		);
	}
	 
	/**
	 * anonymous class to update an entity from the database
	 * @param entity: the updated entity 
	 */

	public void update(EntityModel entity) {
		// execute method of the JpaUtility class with our block of code
	    transaction.execute(
	    		// lambda for writing the anonymous class
	    		(entityManager) -> { 
	    			// update the passed entity
	    			entityManager.merge(entity);
	    			// the method does not return an object
	    			return null;
	    		}
	    );
	}
	  
	/**
	 * anonymous class to remove an entity from the database
	 * @param entity: the entity that shall be removed from the database
	 */
	public void remove(EntityModel entity) {
		// execute method of the JpaUtility class with our block of code
		transaction.execute(
				// lambda for writing the anonymous class
				(entityManager) -> { 
					// remove the passed entity
					entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
					// the method does not return an object
					return null;
				}
		);
	}
	  
	/**
	 * anonymous class to find all object from an entity
	 * @param entityClass: the entity class of the objects that we want to find
	 * @return a list with all objects from an entity
	 */
	public <T> List<T> findAll(Class<T> entityClass) {
		// return the result of the execute method of the JpaUtility class with our block of code
		return transaction.execute(
				// lambda for writing the anonymous class
				(entityManager) -> { 
					// return all objects from the entity
					return entityManager.createQuery(
							"Select objects FROM " + entityClass.getName() + " objects", entityClass)
							.getResultList();				
					}
		);
	}

	/**
	 * getter of the lastId
	 * @return the ID of the last fetched entity
	 */
	public int getLastId() {
		return lastId;
	}

	/**
	 * setter of the lastId
	 * @param lastId: Id of the last fetched entity
	 */
	public void setLastId(int lastId) {
		this.lastId = lastId;
	}
}
