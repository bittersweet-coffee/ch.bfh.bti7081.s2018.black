package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Doctor class
 * @author musaa1
 * @version 0.1
 */
@Entity
@Table(name="doctor")
public class DoctorModel extends EntityModel {

	// firstname of the doctor. Can not be null
	@Column(nullable=false)
	private String firstname;
	
	// lastname of the doctor. Can not be null
	@Column(nullable=false)
	private String lastname;
	
	// list of patients of the doctor
	// is mapped with the variable doctors in the class PatientModel
	@ManyToMany(mappedBy="doctors")
	private List<PatientModel> patients;
	
	// list of the appointments of the doctor
	// this is a many-to-many relation so we need a relation table
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
	private List<AppointmentModel> appointments;
	
	// login user of the doctor
	// user_id is the foreign key. Can not be null
	@OneToOne
	@JoinColumn(name="user_id", nullable=false)
	private UserModel user;
	
	/**
	 * getter of the firstname
	 * @return the firstname of the doctor
	 */
	public String getFirstname() {
		return this.firstname;
	}

	/**
	 * setter of the firstname
	 * @param firstname of the doctor
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * getter of the lastname
	 * @return the lastname of the doctor
	 */
	public String getLastname() {
		return this.lastname;
	}

	/**
	 * setter of the lastname
	 * @param lastname of the doctor
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

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
	 * @return a list with the appointsments of a the doctor
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

	/**
	 * getter of the user from the doctor
	 * @return the login user of the doctor
	 */
	public UserModel getUser() {
		return this.user;
	}

	/**
	 * setter of the user
	 * @param user of the doctor
	 */
	public void setUser(UserModel user) {
		this.user = user;
	}
}
