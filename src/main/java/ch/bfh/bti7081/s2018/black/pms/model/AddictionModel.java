package ch.bfh.bti7081.s2018.black.pms.model;

import javax.persistence.*;

@Entity
public class AddictionModel {

	@Id @GeneratedValue
	private int Id;

	private String name;

	private String description;
	
	public AddictionModel() {
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
