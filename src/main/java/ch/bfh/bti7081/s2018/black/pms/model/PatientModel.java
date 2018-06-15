package ch.bfh.bti7081.s2018.black.pms.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Patient class
 */
@Entity
@Table(name="patient")
public class PatientModel extends EntityModel {
	
	// Firstname of the patient. Can not be null
	@Column(nullable=false)
	private String firstname;
	
	// Lastname of the patient. Can not be null
	@Column(nullable=false)
	private String lastname;
	
	// Street of the place where the patient lives
	private String street;
	
	// Post code of the place where the patient lives
	@Column(name="post_code")
	private int postCode;
	
	// Birthday of the patient
	@Column(columnDefinition = "DATE")
	private LocalDate birthday;
	
	// Telephone number of the patient
	private String telephone;
	
	// List of the drugs that the patient needs
	// This is a many-to-many relation so we need a relation table
	@OneToMany(mappedBy="drug", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PatientDrugModel> drugs;
	
	// List of the addictions that the patient has
	// This is a many-to-many relation so we need a relation table
	@ManyToMany
    @JoinTable(name="patient_addiction",
        joinColumns=
            @JoinColumn(name="patient_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="addiction_id", referencedColumnName="id"))
	private List<AddictionModel> addictions;
	
	// List of the doctors that a patient has
	// This is a many-to-many relation so we need a relation table
	@ManyToMany
    @JoinTable(name="patient_doctor",
        joinColumns=
            @JoinColumn(name="patient_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="doctor_id", referencedColumnName="id"))
	private List<DoctorModel> doctors;
	
	// List of the appointments of the patient
	// This is a many-to-many relation so we need a relation table
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
	private List<AppointmentModel> appointments;
	
	// List of notes of the patient.
	// Is mapped with the variable patient in the class NoticeModel
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
	private List<NoticeModel> notes;
	
	// Location of the patient
	// Location_id is the foreign key. Can not be null
	@ManyToOne
	@JoinColumn(name="clinic_id", nullable=false)
	private ClinicModel clinic;

	/**
	 * Getter for the first name
	 * @return the first name of the patient
	 */
	public String getFirstname() {
		return this.firstname;
	}

	/**
	 * Setter for the first name
	 * @param firstname	of the patient
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Getter for the last name
	 * @return the last name of the patient
	 */
	public String getLastname() {
		return this.lastname;
	}

	/**
	 * Setter for the last name
	 * @param lastname of the patient
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Getter for the street
	 * @return the street of the patient
	 */
	public String getStreet() {
		return this.street;
	}

	/**
	 * Setter for the street
	 * @param street of the patient
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Getter for the post code
	 * @return the post code from the patient
	 */
	public int getPostCode() {
		return this.postCode;
	}

	/**
	 * Setter for the post code
	 * @param postCode of the patient
	 */
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	/**
	 * Getter for the birthday
	 * @return the birthday of the patient
	 */
	public LocalDate getBirthday() {
		return this.birthday;
	}

	/**
	 * Setter for the birthday
	 * @param birthday of the patient
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	/**
	 * Getter for the telephone number
	 * @return the telephone number of the patient
	 */
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * Setter for the telephone number
	 * @param telephone of the patient
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Getter for the drugs
	 * @return a list of the drugs that the patient has to take
	 */
	public List<PatientDrugModel> getDrugs() {
		return this.drugs;
	}

	/**
	 * Setter for the drugs
	 * @param drugs that the patient has to take
	 */
	public void setDrugs(List<PatientDrugModel> drugs) {
		this.drugs = drugs;
	}

	/**
	 * Getter for the addictions
	 * @return a list of the addiction that the patient has
	 */
	public List<AddictionModel> getAddictions() {
		return this.addictions;
	}

	/**
	 * Setter for the addictions
	 * @param addictions that the patient has
	 */
	public void setAddictions(List<AddictionModel> addictions) {
		this.addictions = addictions;
	}

	/**
	 * Getter for the doctors
	 * @return a list of the doctors of the patient
	 */
	public List<DoctorModel> getDoctors() {
		return this.doctors;
	}

	/**
	 * Setter for the doctors
	 * @param doctors of the patient
	 */
	public void setDoctors(List<DoctorModel> doctors) {
		this.doctors = doctors;
	}

	/**
	 * Getter for the appointments
	 * @return a list of the appointments that the patient has
	 */
	public List<AppointmentModel> getAppointments() {
		return this.appointments;
	}

	/**
	 * Setter for the appointments
	 * @param appointments of the patient
	 */
	public void setAppointments(List<AppointmentModel> appointments) {
		this.appointments = appointments;
	}

	/**
	 * Getter for the location
	 * @return the location where the patient gets cured
	 */
	public ClinicModel getClinic() {
		return this.clinic;
	}

	/**
	 * Setter for the location
	 * @param location where the patient gets cured
	 */
	public void setClinic(ClinicModel clinic) {
		this.clinic = clinic;
	}

	/**
	 * Getter for the notes
	 * @return a list with the notes of the patient
	 */
	public List<NoticeModel> getNotes() {
		return this.notes;
	}

	/**
	 * Setter for the notes
	 * @param notes of the patient
	 */
	public void setNotes(List<NoticeModel> notes) {
		this.notes = notes;
	}
}
