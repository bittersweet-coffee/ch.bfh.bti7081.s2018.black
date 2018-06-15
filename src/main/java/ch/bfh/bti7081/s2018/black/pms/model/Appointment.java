package ch.bfh.bti7081.s2018.black.pms.model;

import java.time.LocalDateTime;

/**
 * Appointment class
 * Used in conjunction with the agenda to provide a calendar.
 */
public class Appointment {

	// Id of the appointment
	private int id;
	
	// Date and time variables
	private LocalDateTime start;
	private LocalDateTime end;
	
	// Title and description
	private String title;
	private String description;
	
	// Attending doctor and patient
	private DoctorItem doctorItem;
	private PatientItem patientItem;
	
	public Appointment() {
	}
	
	/**
	 * Create an appointment with a predefined start and end date time.
	 * @param start of the appointment
	 * @param end of the appointment
	 */
	public Appointment(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * Getter for the start date time
	 * @return the start date time
	 */
	public LocalDateTime getStart() {
		return this.start;
	}

	/**
	 * Setter for the start date time
	 * @param the start date time
	 */
	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	/**
	 * Getter for the end date time
	 * @return the end date time
	 */
	public LocalDateTime getEnd() {
		return this.end;
	}

	/**
	 * Setter for the end date time
	 * @param the end of the date time
	 */
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	/**
	 * Getter for the title 
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Setter for the title
	 * @param the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Getter for the description
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Setter for the description
	 * @param the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter for the id
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Setter for the id
	 * @param the id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for the attending doctor
	 * @return the doctor item
	 */
	public DoctorItem getDoctorItem() {
		return doctorItem;
	}

	/**
	 * Setter for the attending doctor
	 * @param the doctor item
	 */
	public void setDoctorItem(DoctorItem doctorItem) {
		this.doctorItem = doctorItem;
	}

	/**
	 * Getter for the attending patient
	 * @return the patient item
	 */
	public PatientItem getPatientItem() {
		return patientItem;
	}

	/**
	 * Setter for the attending patient
	 * @param the patient item
	 */
	public void setPatientItem(PatientItem patientItem) {
		this.patientItem = patientItem;
	}
}
