package ch.bfh.bti7081.s2018.black.pms.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * User class
 * @author musaa1
 * @version 0.1
 */
@MappedSuperclass
public class UserModel extends EntityModel {

	// firstname of the user
	@Column(nullable=false)
	private String firstname;
	
	// lastname of user
	@Column(nullable=false)
	private String lastname;
	
	// username of the user
	@Column(nullable=false)
	private String username;
	
	// passwordHash of the user
	@Column(nullable=false)
	private String passwordHash;
	
	/**
	 * getter of the firstname
	 * @return the firstname of the user
	 */
	public String getFirstname() {
		return this.firstname;
	}

	/**
	 * setter of the firstname
	 * @param firstname of the user
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * getter of the lastname
	 * @return the lastname of the user
	 */
	public String getLastname() {
		return this.lastname;
	}

	/**
	 * setter of the lastname
	 * @param lastname of the user
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * getter of the username 
	 * @return the username of the user
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * setter of the username
	 * @param username of the user
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * getter of the passwordHash
	 * @return the passwordHash of the user
	 */
	public String getPasswordHash() {
		return this.passwordHash;
	}

	/**
	 * setter of the passwordHash
	 * @param passwordHash of the user
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
}
