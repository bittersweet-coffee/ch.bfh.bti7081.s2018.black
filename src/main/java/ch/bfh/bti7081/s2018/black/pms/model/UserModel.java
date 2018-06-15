package ch.bfh.bti7081.s2018.black.pms.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * User class
 */
@MappedSuperclass
public class UserModel extends EntityModel {

	// First name of the user
	@Column(nullable=false)
	private String firstname;
	
	// Last name of user
	@Column(nullable=false)
	private String lastname;
	
	// Username of the user
	@Column(nullable=false)
	private String username;
	
	// passwordHash of the user
	@Column(nullable=false)
	private String passwordHash;
	
	/**
	 * Getter for the first name
	 * @return the first name of the user
	 */
	public String getFirstname() {
		return this.firstname;
	}

	/**
	 * Setter for the first name 
	 * @param first name of the user
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Getter for the last name
	 * @return the last name of the user
	 */
	public String getLastname() {
		return this.lastname;
	}

	/**
	 * Setter for the last name
	 * @param last name of the user
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Getter for the user name 
	 * @return the user name of the user
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Setter for the username
	 * @param username of the user
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter for the passwordHash
	 * @return the passwordHash of the user
	 */
	public String getPasswordHash() {
		return this.passwordHash;
	}

	/**
	 * Setter for the passwordHash
	 * @param passwordHash of the user
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
}
