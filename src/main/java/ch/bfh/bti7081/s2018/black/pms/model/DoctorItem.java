package ch.bfh.bti7081.s2018.black.pms.model;


public class DoctorItem {
	
	private Integer id;
	private String firstName;
	private String lastName;
	
	private DoctorModel model;
	
	public DoctorItem() {
		
	}
	
	public DoctorItem(DoctorModel model) {
		this.model = model;
		reloadFromModel();
	}

	public void reloadFromModel() {
		this.id = model.getId();
		this.firstName = model.getFirstname();
		this.lastName = model.getLastname();
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return firstName + " " + lastName; 
	}
}
