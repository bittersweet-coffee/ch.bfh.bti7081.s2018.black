package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * User class
 * @author musaa1
 * @version 0.1
 */
@Entity
@Table(name="user")
public class UserModel extends EntityModel {
	
	// name of the user
	private String name;
	
	// password of the user
	private String password;
	
	// the date when the user will expire
	@Column(name="expiry_date")
	private Date expiryDate;
	
	// doctor that belongs to the specified user
	@OneToOne(mappedBy="user")
	private DoctorModel doctor;

	/**
	 * getter of the name
	 * @return the name of the user
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * setter of the name
	 * @param name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter of the password
	 * @return the password of the user
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * setter of the password
	 * @param password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * getter of the expire date
	 * @return the date when the user will expire
	 */
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	/**
	 * setter of the expire date
	 * @param expiryDate: Date when the user will expire
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * getter of the doctor
	 * @return the doctor that belongs to the user
	 */
	public DoctorModel getDoctor() {
		return this.doctor;
	}

	/**
	 * setter of the doctor
	 * @param doctor of the user that blongs to it
	 */
	public void setDoctor(DoctorModel doctor) {
		this.doctor = doctor;
	}
}
