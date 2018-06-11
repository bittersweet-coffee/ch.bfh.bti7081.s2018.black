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
 * @author toni
 * @version 0.2
 */
@Entity
@Table(name="appointment")
public class AppointmentModel extends EntityModel{

	// name of the appointment
	private String name;

	// description of the appointment
	private String description;

	// start of the appointment
	private LocalDateTime start;

	// end of the appointment
	private LocalDateTime end;
	
	// period of the appointment
	//private String period;
	
	// date formatter. Is ignored by hibernate
	@Transient
	private DateTimeFormatter dateFormatter;
	
	// time formatter. Is ignored by hibernate
	@Transient
	private DateTimeFormatter timeFormatter;
	
	// list of patients with the same appointment
	// is mapped with the variable appointments in the class PatientModel
	@ManyToOne
	@JoinColumn(name="patient_id")
	private PatientModel patient;
	
	// list of doctors with the same appointment
	// is mapped with the variable appointments in the class DoctorModel
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private DoctorModel doctor;
	
	// location where the appointment will take place
	// location_id is the foreign key. Can not be null
	@ManyToOne
	@JoinColumn(name="clinic_id", nullable=true)
	private ClinicModel clinic;
	
	// Do we really need this constructor
	public AppointmentModel() {
		
	}
	
	// Do we really need this constructor???
	public AppointmentModel(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * getter of the name
	 * @return the name of the appointment
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * setter of the name
	 * @param name of the appointment
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getter of the description
	 * @return the description of the appointment
	 */
	public String getDescription() {
		return this.description;
	}	

	/**
	 * setter of the description
	 * @param description of the appointment
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * getter of the start 
	 * @return the start date time of the appointment
	 */
	public LocalDateTime getStart() {
		return this.start;
	}

	/**
	 * setter of the start
	 * @param start of the appointment
	 */
	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	/**
	 * getter of the end
	 * @return the end date time of the appointment
	 */
	public LocalDateTime getEnd() {
		return this.end;
	}

	/**
	 * setter of the end
	 * @param end of the appointment
	 */
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	/**
	 * getter of the period
	 * @return the period of the appointment
	 */
	/*public String getPeriod() {
		return this.period;
	}

	/**
	 * setter of the period
	 * @param period of the appointment
	 */
	/*public void setPeriod(String period) {
		this.period = period;
	}

	/**
	 * getter of the patients
	 * @return a list of the patients with the same appointment
	 */
	public PatientModel getPatient() {
		return this.patient;
	}

	/**
	 * setter of the patients
	 * @param patients with the same appointment
	 */
	public void setPatient(PatientModel patient) {
		this.patient = patient;
	}

	/**
	 * getter of the doctors
	 * @return a list of the doctors with the same appointment
	 */
	public DoctorModel getDoctor() {
		return this.doctor;
	}

	/**
	 * setter of the doctors
	 * @param doctors with the same appointment
	 */
	public void setDoctor(DoctorModel doctor) {
		this.doctor = doctor;
	}

	/**
	 * getter of the location
	 * @return the location where the appointment takes place
	 */
	public ClinicModel getClinic() {
		return this.clinic;
	}

	/**
	 * setter of the location
	 * @param location where the appointment takes place
	 */
	public void setLocation(ClinicModel clinic) {
		this.clinic = clinic;
	}
}
