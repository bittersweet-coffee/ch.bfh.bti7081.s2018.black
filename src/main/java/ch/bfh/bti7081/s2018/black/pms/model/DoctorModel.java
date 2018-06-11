package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Doctor class
 * @author musaa1
 * @version 0.1
 */
@Entity
@Table(name="doctor")
public class DoctorModel extends UserModel {

	// list of patients of the doctor
	// is mapped with the variable doctors in the class PatientModel
	@ManyToMany(mappedBy="doctors")
	private List<PatientModel> patients;
	
	// list of the appointments of the doctor
	// this is a many-to-many relation so we need a relation table
	@ManyToMany
    @JoinTable(name="doctor_appointment",
        joinColumns=
            @JoinColumn(name="doctor_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="appointment_id", referencedColumnName="id"))
	private List<AppointmentModel> appointments;
	
	/**
	 * getter of the patients
	 * @return a list with the patients of the doctor
	 */
	public List<PatientModel> getPatients() {
		return this.patients;
	}

	/**
	 * setter of the patients
	 * @param patients of the doctor
	 */
	public void setPatients(List<PatientModel> patients) {
		this.patients = patients;
	}

	/**
	 * getter of the appointments of the doctor
	 * @return a list with the appointments of a the doctor
	 */
	public List<AppointmentModel> getAppointments() {
		return this.appointments;
	}

	/**
	 * setter of the appointments
	 * @param appointments of the doctor
	 */
	public void setAppointments(List<AppointmentModel> appointments) {
		this.appointments = appointments;
	}
}
