package ch.bfh.bti7081.s2018.black.pms.util;

import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.EntityModel;

//Source: http://www.copypasteisforword.com/notes/lambda-expressions-in-java
// Author: enrique
// Date: 16.05.2018

public class JpaDataAccessObject {
	 
	  private JpaUtility transaction;
	 
	  public JpaDataAccessObject(JpaUtility transaction) {
	    this.transaction = transaction;
	  }
	 
	  public void store(EntityModel entity) {
	    transaction.execute(
	      (entityManager) -> { entityManager.persist(entity);
	                           return null;
	      });
	  }
	 
	  public void update(EntityModel entity) {
	    transaction.execute(
	      (entityManager) -> { entityManager.merge(entity);
	                		   return null;
	      });
	  }
	 
	  public EntityModel findById(int id, EntityModel entity) {
	    return transaction.execute(
	      (entityManager) -> { return entityManager.find(entity.getClass(), id);
	      });
	  }
	  
	  public <T> List<T> findAll(String entityName) {
		  return transaction.execute(
			(entityManager) -> { //System.out.println(entityManager.createQuery(
								//"Select p FROM " + entityName + " p").getResultList()); 
				return entityManager.createQuery(
								"Select p FROM " + entityName + " p").getResultList();				
			});
	  }
	}
