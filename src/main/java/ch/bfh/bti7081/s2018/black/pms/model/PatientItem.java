package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PatientItem {
	
	private Integer id;
	
	private List<String> notes;
	private PatientModel model;
	private String street;
	private int postcode;
	private String telephone;
	private List<AddictionModel> addictions;
	private List<DoctorModel> doctors;
	private ClinicModel location;
	private List<PatientDrugModel> drugs;
	private List<AppointmentModel> appointments;
	
	
	private String firstName, lastName;
	private Date birthday;
	
	public PatientItem() {
		
	}
	
	public PatientItem(PatientModel model) {
		this.model = model;
		reloadFromModel();
	}

	public void reloadFromModel() {
		this.id = model.getId();
		this.firstName = model.getFirstname();
		this.lastName = model.getLastname();
		this.birthday = model.getBirthday();

		this.notes = new LinkedList<>();
		for (NoticeModel note : model.getNotes()) {
			this.notes.add(note.getNote());
		}
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getNotes() {
		return this.notes;
	}
	
	public String getNotesAsString() {
		String strNotes = "";
		for (String note : this.notes) {
			strNotes = strNotes.concat("- " + note + " \n\n");
		}
		
		// cut the ending line feeds
		if(strNotes.length() > 2) {
			return strNotes.substring(0, strNotes.length()-2);
		}
			
		return strNotes;
	}

	public void setNotes(List<String> notes) {
		this.notes = notes;
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
		return this.firstName + " " + this.lastName; 
	}
	
	public Date getBirthday() {
		return this.birthday;
	}

	public String getBirthdayAsString() {
		return String.valueOf(this.birthday);
	}
	
	public PatientModel getModel() {
		return this.model;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<AddictionModel> getAddictions() {
		return addictions;
	}

	public void setAddictions(List<AddictionModel> addictions) {
		this.addictions = addictions;
	}

	public List<DoctorModel> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<DoctorModel> doctors) {
		this.doctors = doctors;
	}

	public ClinicModel getLocation() {
		return location;
	}

	public void setLocation(ClinicModel location) {
		this.location = location;
	}

	public List<PatientDrugModel> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<PatientDrugModel> drugs) {
		this.drugs = drugs;
	}

	public void setModel(PatientModel model) {
		this.model = model;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<AppointmentModel> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<AppointmentModel> appointments) {
		this.appointments = appointments;
	}
	
	
}
