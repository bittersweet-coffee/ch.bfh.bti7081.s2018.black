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

/**
 * Addiction class
 * @author musaa1
 * version 0.5
 * NamedEntityGraphs are the solution of the LazyInitializationException problem
 * This way can we access the entities in the database with the fetch type lazy
 */
@Entity
@Table(name="addiction")
public class AddictionModel extends EntityModel {

	// name of the addiction
	private String name;

	// description of the addiction. size is set to 1000 characters
	@Column(length=1000)
	private String description;
	
	// list of symptoms of the addiction.
	// is mapped with the variable addiction in the class SymptomModel
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "addiction")
	private List<SymptomModel> symptoms;

	// hibernate ignores this variable. I think we don't need this variable
	@Transient
	private List<String> treatments = new LinkedList<>();;
	
	// hibernate ignores this variable. TODO: Do we have to rename our LocationModel class to ClinicModel?
	@Transient
	private List<String> clinics = new LinkedList<>();;
	
	// list of patients with the same addiction
	// is mapped with the variable addictions in the class PatientModel
	@ManyToMany(mappedBy="addictions")
	private List<PatientModel> patients;

	/**
	 * Getter for name
	 * @return the name of the addiction
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * setter for name
	 * @param name of the addiction
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * setter for description
	 * @param description of the addiction
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * getter for description
	 * @return the description of the addiction
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * getter for patients
	 * @return a list of patients with the addiction
	 */
	public List<PatientModel> getPatients() {
		return this.patients;
	}

	/**
	 * setter for patients
	 * @param patients with the addiction
	 */
	public void setPatients(List<PatientModel> patients) {
		this.patients = patients;
	}
	
	/**
	 * getter for symptoms
	 * @return a list with the symptoms of the addiction
	 */
	public List<SymptomModel> getSymptoms() {
		return this.symptoms;
	}
	
	/**
	 * setter for symptoms
	 * @param symptoms of the addiction
	 */
	public void setSymptoms(List<SymptomModel> symptoms) {
		this.symptoms = symptoms;
	}
	
	/**
	 * getter of the String of a symptom
	 * @return name of the symptom as String
	 */
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
	
	// the variable treatments may be deleted
	public List<String> getTreatments() {
		return this.treatments;
	}
	
	// the variable treatments may be deleted
	public void setTreatments(List<String> treatments) {
		this.treatments = treatments;
	}
	
	/**
	 * getter of the clinics
	 * @return List of clinics where the addiction can be cured
	 */
	public List<String> getClinics() {
		return this.clinics;
	}
	
	/**
	 * setter of clinics
	 * @param clinics where the addiction can be cured
	 */
	public void setClinics(List<String> clinics) {
		this.clinics = clinics;
	}
	
}
