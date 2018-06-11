package ch.bfh.bti7081.s2018.black.pms.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="notice")
public class NoticeModel extends EntityModel {

	private String note;
	
	// Patients that are associated with the note
	// patient_id is the foreign key. Can not be null
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="patient_id")
	private PatientModel patient;
	
	/**
	 * getter of the note
	 * @return the note as String
	 */
	public String getNote() {
		return this.note;
	}

	/**
	 * setter of the note
	 * @param note of the object
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * getter of the patient
	 * @return the patient that has the note
	 */
	public PatientModel getPatient() {
		return this.patient;
	}

	/**
	 * setter of the patient
	 * @param patient that has the note
	 */
	public void setPatient(PatientModel patient) {
		this.patient = patient;
	}
}
