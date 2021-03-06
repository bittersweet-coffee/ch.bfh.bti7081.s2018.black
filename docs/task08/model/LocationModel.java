package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Location class
 * @author musaa1
 * @version 0.1
 */
@Entity
@Table(name="location")
public class LocationModel extends EntityModel {

	// name of the location
	private String name;
	
	// street of the location
	private String street;
	
	// post code of the location
	@Column(name="`post_code`")
	private int postCode;
	
	// place of the location
	private String place;
	
	// telephone number of the location
	private String telephone;
	
	// email address of the location
	private String email;
	
	// list of patients that are cured in the location
	// is mapped with the variable location of the class PatientModel
	@OneToMany(mappedBy = "location")
	private List<PatientModel> patients;
	
	// list of the appointments of the location
	// is mapped with the variable location of the class AppointmentModel
	@OneToMany(mappedBy = "location")
	private List<AppointmentModel> appointments;
	
	/**
	 * getter of the name
	 * @return the name of the location
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * setter of the name
	 * @param name of the location
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter of the street
	 * @return the street of the location
	 */
	public String getStreet() {
		return this.street;
	}

	/**
	 * setter of the street
	 * @param street of the location
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * getter of the post code
	 * @return the post code of the location
	 */
	public int getPostCode() {
		return this.postCode;
	}

	/**
	 * setter of the post code
	 * @param postCode of the location
	 */
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	/**
	 * getter of the place
	 * @return the place of the location
	 */
	public String getPlace() {
		return this.place;
	}

	/**
	 * setter of the place
	 * @param place of the location
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * getter of the telephone number
	 * @return the telephone number of the location
	 */
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * setter of the telephone number
	 * @param telephone of the location
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * getter of the email address
	 * @return email address of the location
	 */
	public String getemail() {
		return this.email;
	}

	/**
	 * setter of the email address
	 * @param email of the location
	 */
	public void setemail(String email) {
		this.email = email;
	}

	/**
	 * getter of the patients
	 * @return a list of patients that are cured at the location
	 */
	public List<PatientModel> getPatients() {
		return this.patients;
	}

	/**
	 * setter of the patients
	 * @param patients of the location
	 */
	public void setPatients(List<PatientModel> patients) {
		this.patients = patients;
	}

	/**
	 * getter of the appointments
	 * @return a list of the appointments of the location
	 */
	public List<AppointmentModel> getAppointments() {
		return this.appointments;
	}

	/**
	 * setter of the appointments
	 * @param appointments of the location
	 */
	public void setAppointments(List<AppointmentModel> appointments) {
		this.appointments = appointments;
	}
}
