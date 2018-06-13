package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.EntityModel;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaUtility;

/**
 * class JpaServicePresenter
 * @author musaa1
 * @version 1.0
 * This class interacts with the JpaService interface. This class
 * is needed from all Presenter classes to have access to the database
 */
public class JpaServicePresenter {		
	
	/**
	 * method that will get all entity objects
	 * @param entityClass of the entity that shall be fetched
	 * @return a list with all objects from an entity
	 */
	public static <T> List<T> findAll(Class<T> entityClass) {
		return getService().findAll(entityClass);
	}
	
	/**
	 * method to store the entity to the database
	 * @param entity that shall be persisted in the database
	 */
	public static void store(EntityModel model) {
		getService().store(model);
	}
	
	/**
	 * method to update an entity object in the database
	 * @param entity that shall be updated in the database
	 */
	public static void update(EntityModel model) {
		getService().update(model);
	}
	
	/**
	 * method to remove an entity from the database
	 * @param entity that shall be removed from the database
	 */
	public static void remove(EntityModel model) {
		getService().remove(model);
	}
	
	/**
	 * method to get the last id from the last fetched entity
	 * @return the last id
	 */
	public static int getLastId() {
		return getService().getLastId();
	}
	
	/**
	 * helper method to get a transaction
	 * @return a JpaService service
	 */
	private static JpaService getService() {
		JpaUtility transaction = new JpaUtility();
		JpaService service = new JpaDataAccessObject(transaction);
		return service;
	}
}
