package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Addiction class
 * NamedEntityGraphs are the solution of the LazyInitializationException problem
 * This way can we access the entities in the database with the fetch type lazy
 */
@Entity
@Table(name="addiction")
public class AddictionModel extends EntityModel {

	// Name of the addiction
	private String name;

	// Description of the addiction
	// Size is set to 1000 characters
	@Column(length=1000)
	private String description;
	
	// List of symptoms of the addiction
	// Is mapped with the variable addiction in the class SymptomModel
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "addiction")
	private List<SymptomModel> symptoms;

	// List of the clinics where the addiction is treated
	// Is mapped with the variable addictions of the class AddictionModel
    @ManyToMany(mappedBy="addictions")
	private List<ClinicModel> clinics;
	
	// List of patients with the same addiction
	// Is mapped with the variable addictions in the class PatientModel
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
	 * Setter for name
	 * @param name of the addiction
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter for description
	 * @param description of the addiction
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter for description
	 * @return the description of the addiction
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Getter for patients
	 * @return a list of patients with the addiction
	 */
	public List<PatientModel> getPatients() {
		return this.patients;
	}

	/**
	 * Setter for patients
	 * @param patients with the addiction
	 */
	public void setPatients(List<PatientModel> patients) {
		this.patients = patients;
	}
	
	/**
	 * Getter for symptoms
	 * @return a list with the symptoms of the addiction
	 */
	public List<SymptomModel> getSymptoms() {
		return this.symptoms;
	}
	
	/**
	 * Setter for symptoms
	 * @param symptoms of the addiction
	 */
	public void setSymptoms(List<SymptomModel> symptoms) {
		this.symptoms = symptoms;
	}
	
	/**
	 * Getter of the string of a symptom
	 * @return name of the symptom as string
	 */
	public String getSymptomsAsString() {
		String symptomString = "";
		for (SymptomModel symptom : this.symptoms) {
			symptomString = symptomString.concat("- " + symptom.getDescription() + "\n\n");
		}

		// cut the ending line feeds
		if (symptomString.length() > 2) {
			return symptomString.substring(0, symptomString.length()-2);
		} else {
			return symptomString;
		}
	}
	
	/**
	 * Getter of the clinics
	 * @return list of clinics where the addiction can be cured
	 */
	public List<ClinicModel> getClinics() {
		return this.clinics;
	}
	
	/**
	 * Setter of clinics
	 * @param clinics where the addiction can be cured
	 */
	public void setClinics(List<ClinicModel> clinics) {
		this.clinics = clinics;
	}
}
