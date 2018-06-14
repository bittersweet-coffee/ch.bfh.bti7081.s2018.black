package ch.bfh.bti7081.s2018.black.pms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PatientDrugModel class
 * @author musaa1
 * @version 1.0
 * This class is needed because the relationship table patient_drug needs
 * an extra attribute dose
 */
@Entity
@Table(name="patient_drug")
public class PatientDrugModel extends EntityModel {

	// patient in a relationship with a drug
	@ManyToOne
	@JoinColumn(name="patient_id")
	private PatientModel patient;
	
	// drug in a relationship with a patient
	@ManyToOne
	@JoinColumn(name="drug_id")
	private DrugModel drug;
	
	// subscribed dose of the drug
	private Double dose;

	/**
	 * getter of the patient in a relationship
	 * @return the patient
	 */
	public PatientModel getPatient() {
		return this.patient;
	}

	/**
	 * setter of the patient
	 * @param patient that is in a relationship with a drug
	 */
	public void setPatient(PatientModel patient) {
		this.patient = patient;
	}

	/**
	 * getter of the drug in a relationship
	 * @return a drug
	 */
	public DrugModel getDrug() {
		return this.drug;
	}

	/**
	 * setter of the durg
	 * @param drug that is in a relationship with a patient
	 */
	public void setDrug(DrugModel drug) {
		this.drug = drug;
	}

	/**
	 * getter of the dose
	 * @return the subscribed dose of the drug
	 */
	public Double getDose() {
		return this.dose;
	}

	/**
	 * setter of the dose
	 * @param dose that the patient has get subscribed
	 */
	public void setDose(Double dose) {
		this.dose = dose;
	}
}
