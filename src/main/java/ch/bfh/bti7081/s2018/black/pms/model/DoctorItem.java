package ch.bfh.bti7081.s2018.black.pms.model;

/**
 * Doctor item class
 */
public class DoctorItem {
	
	private Integer id;
	private String firstName;
	private String lastName;
	
	private DoctorModel model;
	
	public DoctorItem() {
	}
	
	/**
	 * Initialize the doctor item
	 * @param model of the doctor
	 */
	public DoctorItem(DoctorModel model) {
		this.model = model;
		reloadFromModel();
	}

	/**
	 * Reload data from model
	 */
	public void reloadFromModel() {
		this.id = model.getId();
		this.firstName = model.getFirstname();
		this.lastName = model.getLastname();
	}
	
	/**
	 * Getter for the id
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Setter for the id
	 * @param id of the doctor
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Getter for the first name
	 * @return first name of the doctor
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Setter for the first name
	 * @param first name of the doctor
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for the last name
	 * @return last name of the doctor
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Setter for the last name
	 * @param last name of the doctor
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Getter for the full name
	 * @return the full name of the doctor
	 */
	public String getFullName() {
		return this.firstName + " " + this.lastName; 
	}
}
