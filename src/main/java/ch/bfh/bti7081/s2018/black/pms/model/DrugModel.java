package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="drug")
public class DrugModel {
	
	@ Id
	@ GeneratedValue
	private int id;
	
	private String name;
	
    @ManyToMany(mappedBy="drugs")
	private List<PatientModel> patients;
    
    public DrugModel(String name, List<PatientModel> patients) {
    	this.name = name;
    	this.patients = patients;
    }

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
