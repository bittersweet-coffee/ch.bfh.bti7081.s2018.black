package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="addiction")
public class AddictionModel extends EntityModel {

	private String name;

	@Column(length=1000)
	private String description;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "addiction")
	private List<SymptomModel> symptoms;
	
	@Transient
	private List<String> treatments = new LinkedList<>();;
	
	@Transient
	private List<String> clinics = new LinkedList<>();;
	
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
	
	public List<PatientModel> getPatients() {
		return patients;
	}

	public void setPatients(List<PatientModel> patients) {
		this.patients = patients;
	}
	
	public List<SymptomModel> getSymptoms() {
		return this.symptoms;
	}
	
	public void setSymptoms(List<SymptomModel> symptoms) {
		this.symptoms = symptoms;
	}
	
	public String getSymptomsAsString() {
		String symptomString = "";
		for (SymptomModel symptom : this.symptoms) {
			symptomString = symptomString.concat("- " + symptom.getDescription() + "\n\n");
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
