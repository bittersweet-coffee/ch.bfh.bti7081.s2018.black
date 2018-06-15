package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Doctor class
 */
@Entity
@Table(name="doctor")
public class DoctorModel extends UserModel {

	// List of patients of the doctor
	// Is mapped with the variable doctors in the class PatientModel
	@ManyToMany(mappedBy="doctors")
	private List<PatientModel> patients;
	
	// List of the appointments of the doctor
	// This is a many-to-many relation so we need a relation table
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
	private List<AppointmentModel> appointments;
	
	/**
	 * Getter for the patients
	 * @return a list with the patients of the doctor
	 */
	public List<PatientModel> getPatients() {
		return this.patients;
	}

	/**
	 * Setter for the patients
	 * @param patients of the doctor
	 */
	public void setPatients(List<PatientModel> patients) {
		this.patients = patients;
	}

	/**
	 * Getter for the appointments of the doctor
	 * @return a list with the appointments of a the doctor
	 */
	public List<AppointmentModel> getAppointments() {
		return this.appointments;
	}

	/**
	 * Setter for the appointments
	 * @param appointments of the doctor
	 */
	public void setAppointments(List<AppointmentModel> appointments) {
		this.appointments = appointments;
	}
}
