package ch.bfh.bti7081.s2018.black.pms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Symptom class
 */
@Entity
@Table(name="symptom")
public class SymptomModel extends EntityModel {
	
	// Description of the symptom
	private String description;

	// Addiction that belongs to the symptom. In our case all symptoms are unique 
	// Addiction_id is the foreign key, can not be null
	@ManyToOne
	@JoinColumn(name="addiction_id", nullable=false)
	private AddictionModel addiction;

	/**
	 * Getter for the description
	 * @return the description of the symptom
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Setter for the description
	 * @param description of the symptom
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter for the addiction
	 * @return the addiction that belongs to the symptom
	 */
	public AddictionModel getAddiction() {
		return this.addiction;
	}

	/**
	 * Setter for the addiction
	 * @param addiction that belongs to the symptom
	 */
	public void setAddiction(AddictionModel addiction) {
		this.addiction = addiction;
	}
}
