package ch.bfh.bti7081.s2018.black.pms.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * This class is the superclass of all entities objects
 */
@MappedSuperclass
public abstract class EntityModel {

	/**
	 * The variable id will be inherited by all
	 * subclasses and will be the primary key
	 * of the entities
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	/**
	 * Getter for id
	 * @return id of the entity
	 */
	public int getId() {
		return this.id;
	}
}
