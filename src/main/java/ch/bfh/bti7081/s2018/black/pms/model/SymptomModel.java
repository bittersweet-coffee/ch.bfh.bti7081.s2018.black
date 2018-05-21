package ch.bfh.bti7081.s2018.black.pms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="symptom")
public class SymptomModel extends EntityModel {
	
	String description;

	@ManyToOne
	@JoinColumn(name="addiction_id", nullable=false)
	private AddictionModel addiction;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AddictionModel getAddiction() {
		return addiction;
	}

	public void setAddiction(AddictionModel addiction) {
		this.addiction = addiction;
	}
}
