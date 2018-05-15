package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="addiction")
public class AddictionModel {

	@Id @GeneratedValue
	private int id;

	private String name;

	private String description;
	
	@ManyToMany(mappedBy="addictions")
	private List<PatientModel> patients;
	
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
