package ch.bfh.bti7081.s2018.black.pms.util;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.NamedEntityGraph;

import ch.bfh.bti7081.s2018.black.pms.model.EntityModel;

//Source: http://www.copypasteisforword.com/notes/lambda-expressions-in-java
// Author: enrique
// Date: 16.05.2018

public class JpaDataAccessObject {
	 
	  private JpaUtility transaction;
	  private static final String JAVAX_PERSISTENCE_FETCHGRAPH = "javax.persistence.fetchgraph";
	 
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
	  
	  public <T> List<T> findAll(Class<T> entityClass) {
		  return transaction.execute(
			(entityManager) -> { 
				EntityGraph<?> entityGraph = 
						entityManager.getEntityGraph(entityClass.getAnnotation(NamedEntityGraph.class).name());			
				return entityManager.createQuery(
					"Select objects FROM " + entityClass.getName() + " objects", entityClass)
					.setHint(JAVAX_PERSISTENCE_FETCHGRAPH, entityGraph)
					.getResultList();				
			});
	  }
	}
