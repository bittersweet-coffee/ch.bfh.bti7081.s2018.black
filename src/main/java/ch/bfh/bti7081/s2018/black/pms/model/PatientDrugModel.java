package ch.bfh.bti7081.s2018.black.pms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="patient_drug")
public class PatientDrugModel extends EntityModel {

	@ManyToOne
	@JoinColumn(name="patient_id")
	private PatientModel patient;
	
	@ManyToOne
	@JoinColumn(name="drug_id")
	private DrugModel drug;
	
	private Double dose;

	public PatientModel getPatient() {
		return this.patient;
	}

	public void setPatient(PatientModel patient) {
		this.patient = patient;
	}

	public DrugModel getDrug() {
		return this.drug;
	}

	public void setDrug(DrugModel drug) {
		this.drug = drug;
	}

	public Double getDose() {
		return this.dose;
	}

	public void setDose(Double dose) {
		this.dose = dose;
	}
}
