package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.EntityModel;

/**
 * Interface JpaService
 * @author musaa1
 * @version 1.0
 * Interface for the association with the package persistence
 * Here all defined all methods, wehre are implemented in JpaDataAccessObject
 */
public interface JpaService {
	
	/**
	 * method to store the entity to the database
	 * @param entity that shall be persisted in the database
	 */
	void store(EntityModel entity);
	
	/**
	 * method to update an entity object in the database
	 * @param entity that shall be updated in the database
	 */
	void update(EntityModel entity);
	
	/**
	 * method to remove an entity from the database
	 * @param entity that shall be removed from the database
	 */
	void remove(EntityModel entity);
	
	/**
	 * method that will get all entity objects
	 * @param entityClass of the entity that shall be fetched
	 * @return a list with all objects from an entity
	 */
	<T> List<T> findAll(Class<T> entityClass);
			
	/**
	 * method to get the last id from the last fetched entity
	 * @return the last id
	 */
	int getLastId();
	
	/**
	 * method to set the last id from the last fetched entity
	 * @param lastId of the last fetched entity
	 */
	void setLastId(int lastId);
}
