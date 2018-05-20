package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.LinkedList;
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
	
	private List<String> symptoms = new LinkedList<>();
	
	private List<String> treatments = new LinkedList<>();;
	
	private List<String> clinics = new LinkedList<>();;
	
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
	
	public void setSymptoms(List<String> symptoms) {
		this.symptoms = symptoms;
	}
	
	public String getSymptomsAsString() {
		String symptomString = "";
		for (String symptom : this.symptoms) {
			symptomString = symptomString.concat(symptom + "\n\n");
		}
		
		if(symptomString.length() > 2) // cut the ending line feeds
			return symptomString.substring(0, symptomString.length()-2);
		else 
			return symptomString;
	}
	
	public List<String> getTreatments() {
		return this.treatments;
	}
	
	public void setTreatments(List<String> treatments) {
		this.treatments = treatments;
	}
	
	public List<String> getClinics() {
		return this.clinics;
	}
	
	public void setClinics(List<String> clinics) {
		this.clinics = clinics;
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
	
}
