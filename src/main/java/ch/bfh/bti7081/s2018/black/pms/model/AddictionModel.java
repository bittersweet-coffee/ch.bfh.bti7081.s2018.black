package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="addiction")
public class AddictionModel {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;
	
	private List<String> symptoms;
	
	private List<String> treatments;
	
	private List<String> clinics;
	
	@ManyToMany(mappedBy="addictions")
	private List<PatientModel> patients;

	public int getId() {
		return id;
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
	
	public List<PatientModel> getPatients() {
		return patients;
	}

	public void setPatients(List<PatientModel> patients) {
		this.patients = patients;
	}
	
	public List<String> getSymptoms() {
		return this.symptoms;
	}
	
	public List<String> getTreatments() {
		return this.treatments;
	}
	
	public List<String> getClinics() {
		return this.clinics;
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
	
}
