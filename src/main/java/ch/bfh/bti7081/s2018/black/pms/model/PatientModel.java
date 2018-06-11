package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Patient class
 * @author musaa1
 * @version 0.1
 */
@Entity
@Table(name="patient")
public class PatientModel extends EntityModel {
	
	// firstname of the patient. Can not be null
	@Column(nullable=false)
	private String firstname;
	
	// lastname of the patient. Can not be null
	@Column(nullable=false)
	private String lastname;
	
	// street of the place where the patient lives
	private String street;
	
	// post code of the place where the patient lives
	@Column(name="post_code")
	private int postCode;
	
	// birthday of the patient
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	// telephone number of the patient
	private String telephone;
	
	// list of the drugs that the patient needs
	// this is a many-to-many relation so we need a relation table
	@OneToMany(mappedBy="drug", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PatientDrugModel> drugs;
	
	// list of the addictions that the patient has
	// this is a many-to-many relation so we need a relation table
	@ManyToMany
    @JoinTable(name="patient_addiction",
        joinColumns=
            @JoinColumn(name="patient_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="addiction_id", referencedColumnName="id"))
	private List<AddictionModel> addictions;
	
	// list of the doctors that a patient has
	// this is a many-to-many relation so we need a relation table
	@ManyToMany
    @JoinTable(name="patient_doctor",
        joinColumns=
            @JoinColumn(name="patient_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="doctor_id", referencedColumnName="id"))
	private List<DoctorModel> doctors;
	
	// list of the appointments of the patient
	// this is a many-to-many relation so we need a relation table
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
	private List<AppointmentModel> appointments;
	
	// list of notes of the patient.
	// is mapped with the variable patient in the class NoticeModel
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
	private List<NoticeModel> notes;
	
	// location of the patient
	// location_id is the foreign key. Can not be null
	@ManyToOne
	@JoinColumn(name="location_id", nullable=false)
	private LocationModel location;

	/**
	 * getter of the first name
	 * @return the first name of the patient
	 */
	public String getFirstname() {
		return this.firstname;
	}

	/**
	 * setter of the first name
	 * @param firstname	of the patient
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * getter of the last name
	 * @return the last name of the patient
	 */
	public String getLastname() {
		return this.lastname;
	}

	/**
	 * setter of the last name
	 * @param lastname of the patient
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * getter of the street
	 * @return the street of the patient
	 */
	public String getStreet() {
		return this.street;
	}

	/**
	 * setter of the street
	 * @param street of the patient
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * getter of the post code
	 * @return the post code from the patient
	 */
	public int getPostCode() {
		return this.postCode;
	}

	/**
	 * setter of the post code
	 * @param postCode of the patient
	 */
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	/**
	 * getter of the birthday
	 * @return the birthday of the patient
	 */
	public Date getBirthday() {
		return this.birthday;
	}

	/**
	 * setter of the birthday
	 * @param birthday of the patient
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * getter of the telephone number
	 * @return the telephone number of the patient
	 */
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * setter of the telephone number
	 * @param telephone of the patient
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * getter of the drugs
	 * @return a list of the drugs that the patient has to take
	 */
	public List<PatientDrugModel> getDrugs() {
		return this.drugs;
	}

	/**
	 * setter of the drugs
	 * @param drugs that the patient has to take
	 */
	public void setDrugs(List<PatientDrugModel> drugs) {
		this.drugs = drugs;
	}

	/**
	 * getter of the addictions
	 * @return a list of the addiction that the patient has
	 */
	public List<AddictionModel> getAddictions() {
		return this.addictions;
	}

	/**
	 * setter of the addictions
	 * @param addictions that the patient has
	 */
	public void setAddictions(List<AddictionModel> addictions) {
		this.addictions = addictions;
	}

	/**
	 * getter of the doctors
	 * @return a list of the doctors of the patient
	 */
	public List<DoctorModel> getDoctors() {
		return this.doctors;
	}

	/**
	 * setter of the doctors
	 * @param doctors of the patient
	 */
	public void setDoctors(List<DoctorModel> doctors) {
		this.doctors = doctors;
	}

	/**
	 * getter of the appointments
	 * @return a list of the appointments that the patient has
	 */
	public List<AppointmentModel> getAppointments() {
		return this.appointments;
	}

	/**
	 * setter of the appointments
	 * @param appointments of the patient
	 */
	public void setAppointments(List<AppointmentModel> appointments) {
		this.appointments = appointments;
	}

	/**
	 * getter of the location
	 * @return the location where the patient gets cured
	 */
	public LocationModel getLocation() {
		return this.location;
	}

	/**
	 * seeter of the location
	 * @param location where the patient gets cured
	 */
	public void setLocation(LocationModel location) {
		this.location = location;
	}

	/**
	 * getter of the notes
	 * @return a list with the notes of the patient
	 */
	public List<NoticeModel> getNotes() {
		return this.notes;
	}

	/**
	 * setter of the notes
	 * @param notes of the patient
	 */
	public void setNotes(List<NoticeModel> notes) {
		this.notes = notes;
	}
}
