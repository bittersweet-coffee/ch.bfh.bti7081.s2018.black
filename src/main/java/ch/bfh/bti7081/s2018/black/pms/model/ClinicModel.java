package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clinic class
 */
@Entity
@Table(name="clinic")
public class ClinicModel extends EntityModel {

	// Name of the location
	private String name;
	
	// Street of the location
	private String street;
	
	// Post code of the location
	@Column(name="`post_code`")
	private int postCode;
	
	// Place of the location
	private String place;
	
	// Telephone number of the location
	private String telephone;
	
	// Email address of the location
	private String email;
	
	// List of patients that are cured in the location
	// Is mapped with the variable location of the class PatientModel
	@OneToMany(mappedBy = "clinic")
	private List<PatientModel> patients;
	
	// List of the appointments of the location
	// Is mapped with the variable location of the class AppointmentModel
	@OneToMany(mappedBy = "clinic")
	private List<AppointmentModel> appointments;
	
	// List of the addictions that the clinic treats
	// This is a many-to-many relation so we need a relation table
	@ManyToMany
    @JoinTable(name="clinic_addiction",
        joinColumns=
            @JoinColumn(name="clinic_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="addiction_id", referencedColumnName="id"))
	private List<AddictionModel> addictions;
	
	/**
	 * Getter for the name
	 * @return the name of the location
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setter for the name
	 * @param name of the location
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the street
	 * @return the street of the location
	 */
	public String getStreet() {
		return this.street;
	}

	/**
	 * Setter for the street
	 * @param street of the location
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Getter for the post code
	 * @return the post code of the location
	 */
	public int getPostCode() {
		return this.postCode;
	}

	/**
	 * Setter for the post code
	 * @param postCode of the location
	 */
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	/**
	 * Getter for the place
	 * @return the place of the location
	 */
	public String getPlace() {
		return this.place;
	}

	/**
	 * Setter for the place
	 * @param place of the location
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * Getter for the telephone number
	 * @return the telephone number of the location
	 */
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * Setter for the telephone number
	 * @param telephone of the location
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Getter for the email address
	 * @return email address of the location
	 */
	public String getemail() {
		return this.email;
	}

	/**
	 * Getter for the patients
	 * @return a list of patients that are cured at the location
	 */
	public List<PatientModel> getPatients() {
		return this.patients;
	}

	/**
	 * Setter for the patients
	 * @param patients of the location
	 */
	public void setPatients(List<PatientModel> patients) {
		this.patients = patients;
	}

	/**
	 * Getter for the appointments
	 * @return a list of the appointments of the location
	 */
	public List<AppointmentModel> getAppointments() {
		return this.appointments;
	}

	/**
	 * Setter for the appointments
	 * @param appointments of the location
	 */
	public void setAppointments(List<AppointmentModel> appointments) {
		this.appointments = appointments;
	}

	/**
	 * Getter for the email address
	 * @return the email address of the clinic
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Setter for the email address
	 * @param email address of the clinic
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter for the addictions
	 * @return the addictions that the clinic treats
	 */
	public List<AddictionModel> getAddictions() {
		return this.addictions;
	}

	/**
	 * Setter for the addictions
	 * @param addictions that the clinic treats
	 */
	public void setAddictions(List<AddictionModel> addictions) {
		this.addictions = addictions;
	}
	
	/**
	 * Getter for the addictions as strings
	 * @return the addiction as string
	 */
	public String getAddictionsAsString() {
		String addictString = "";
		for (AddictionModel addict : this.addictions) {
			addictString = addictString.concat("- " + addict.getName() + "\n\n");
		}

		// cut the ending line feeds
		if (addictString.length() > 2) {
			return addictString.substring(0, addictString.length()-2);
		} else  {
			return addictString;
		}
	}
}
