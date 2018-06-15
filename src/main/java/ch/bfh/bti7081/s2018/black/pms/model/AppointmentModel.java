package ch.bfh.bti7081.s2018.black.pms.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Appointment class
 * Represents the table schema of the database
 */
@Entity
@Table(name="appointment")
public class AppointmentModel extends EntityModel{

	// Name of the appointment
	private String name;

	// Description of the appointment
	private String description;

	// Start of the appointment
	private LocalDateTime start;

	// End of the appointment
	private LocalDateTime end;
	
	// Date formatter, ignored by hibernate
	@Transient
	private DateTimeFormatter dateFormatter;
	
	// Time formatter, ignored by hibernate
	@Transient
	private DateTimeFormatter timeFormatter;
	
	// List of patients with the same appointment
	// Is mapped with the variable appointments in the class PatientModel
	@ManyToOne
	@JoinColumn(name="patient_id")
	private PatientModel patient;
	
	// List of doctors with the same appointment
	// Is mapped with the variable appointments in the class DoctorModel
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private DoctorModel doctor;
	
	// Location where the appointment will take place
	// Location_id is the foreign key, can not be null
	@ManyToOne
	@JoinColumn(name="clinic_id", nullable=true)
	private ClinicModel clinic;
	
	public AppointmentModel() {
	}
	
	/**
	 * Initialize an appointment with a predefined start and end date time
	 * @param the start date time
	 * @param the end date time
	 */
	public AppointmentModel(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * Getter for the name
	 * @return the name of the appointment
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setter for the name
	 * @param name of the appointment
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the description
	 * @return the description of the appointment
	 */
	public String getDescription() {
		return this.description;
	}	

	/**
	 * Setter for the description
	 * @param description of the appointment
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter for the start 
	 * @return the start date time of the appointment
	 */
	public LocalDateTime getStart() {
		return this.start;
	}

	/**
	 * Setter for the start
	 * @param start of the appointment
	 */
	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	/**
	 * Getter for the end
	 * @return the end date time of the appointment
	 */
	public LocalDateTime getEnd() {
		return this.end;
	}

	/**
	 * Setter for the end
	 * @param end of the appointment
	 */
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	/**
	 * Getter for the patients
	 * @return a list of the patients with the same appointment
	 */
	public PatientModel getPatient() {
		return this.patient;
	}

	/**
	 * Setter for the patients
	 * @param patients with the same appointment
	 */
	public void setPatient(PatientModel patient) {
		this.patient = patient;
	}

	/**
	 * Getter for the doctors
	 * @return a list of the doctors with the same appointment
	 */
	public DoctorModel getDoctor() {
		return this.doctor;
	}

	/**
	 * Setter for the doctors
	 * @param doctors with the same appointment
	 */
	public void setDoctor(DoctorModel doctor) {
		this.doctor = doctor;
	}

	/**
	 * Getter for the location
	 * @return the location where the appointment takes place
	 */
	public ClinicModel getClinic() {
		return this.clinic;
	}

	/**
	 * Setter for the location
	 * @param location where the appointment takes place
	 */
	public void setLocation(ClinicModel clinic) {
		this.clinic = clinic;
	}
}
