package ch.bfh.bti7081.s2018.black.pms.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * PatientItem class
 */
public class PatientItem {
	
	private Integer id;
	
	private List<String> notes;
	private PatientModel model;
	private String street;
	private int postcode;
	private String telephone;
	private List<AddictionModel> addictions;
	private List<DoctorModel> doctors;
	private ClinicModel clinic;
	private List<PatientDrugModel> drugs;
	private List<AppointmentModel> appointments;
  
	private String firstName, lastName;
	private LocalDate birthday;
	
	/**
	 * Constructor for the PatientItem
	 * Needed to create a new instance via reflection by persistence framework.
	 */
	public PatientItem() {
	}
	
	/**
	 * Constructor for the PatientItem
	 * Includes the PatientModel and reloads the entries
	 */
	public PatientItem(PatientModel model) {
		this.model = model;
		reloadFromModel();
	}

	/**
	 * Method to reload all needed informations about the patient
	 */
	public void reloadFromModel() {
		this.id = model.getId();
		this.firstName = model.getFirstname();
		this.lastName = model.getLastname();
		this.birthday = model.getBirthday();
		this.street = model.getStreet();
		this.postcode = model.getPostCode();
		this.telephone = model.getTelephone();
		this.clinic = model.getClinic();
		this.drugs = new LinkedList<>();
		this.doctors = new LinkedList<>();
		this.notes = new LinkedList<>();
		this.addictions = new LinkedList<>();
		this.appointments = new LinkedList<>();

		if (model.getDrugs() != null) {
			for (PatientDrugModel drug : model.getDrugs()) {
				this.drugs.add(drug);
			}
		}
			
		for (DoctorModel doctor : model.getDoctors()) {
			this.doctors.add(doctor);
		}
		
		for (NoticeModel note : model.getNotes()) {
			this.notes.add(note.getNote());
		}
		
		for (AddictionModel addiction : model.getAddictions()) {
			this.addictions.add(addiction);
		}
			
		if (model.getAppointments() != null) {
			for (AppointmentModel appointment : model.getAppointments()) {
				this.appointments.add(appointment);
			}
		}
	}
	
	/**
	 * Getter for notes as string
	 * @return notes as a string
	 */
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

	/**
	 * Getter for the id
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Setter for the id
	 * @param id of the patient
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter for the ntoes
	 * @return list of notes
	 */
	public List<String> getNotes() {
		return this.notes;
	}

	/**
	 * Setter for the notes
	 * @param notes as a list
	 */
	public void setNotes(List<String> notes) {
		this.notes = notes;
	}

	/**
	 * Getter for the first name
	 * @return the first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Setter for the first name
	 * @param firstName of the patient
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for the last name
	 * @return the last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Setter for the last name
	 * @param last name of the patient
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Getter for the full name
	 * @return the full name
	 */
	public String getFullName() {
		return this.firstName + " " + this.lastName; 
	}
	
	/**
	 * Getter for the birthday
	 * @return the birthday of the patient
	 */
	public LocalDate getBirthday() {
		return this.birthday;
	}

	/**
	 * Getter for the birthday as a string
	 * @return the birthday string
	 */
	public String getBirthdayAsString() {
		return String.valueOf(this.birthday);
	}

	/**
	 * Setter for the birthday
	 * @param birthday of the patient
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	/** Getter for the patient model
	 * @return the patient model
	 */
	public PatientModel getModel() {
		return this.model;
	}

	/**
	 * Setter for the model
	 * @param model of the patient
	 */
	public void setModel(PatientModel model) {
		this.model = model;
	}
	
	/**
	 * Getter for the street
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Setter for the street
	 * @param the street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Getter for the post code
	 * @return the post code
	 */
	public int getPostcode() {
		return postcode;
	}

	/**
	 * Setter for the post code
	 * @param postcode of the patient
	 */
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	/**
	 * Getter for the telephone
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * Setter for the telephone
	 * @param telephone of the patient
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Getter for the addictions
	 * @return list of addictions
	 */
	public List<AddictionModel> getAddictions() {
		return addictions;
	}

	/**
	 * Setter for the addictions
	 * @param addictions of the patient
	 */
	public void setAddictions(List<AddictionModel> addictions) {
		this.addictions = addictions;
	}

	/**
	 * Getter for the doctors
	 * @return list of doctors
	 */
	public List<DoctorModel> getDoctors() {
		return doctors;
	}

	/**
	 * Setter for the doctors
	 * @param doctors of the patient
	 */
	public void setDoctors(List<DoctorModel> doctors) {
		this.doctors = doctors;
	}

	/**
	 * Getter for the clinic
	 * @return the clinic
	 */
	public ClinicModel getClinic() {
		return this.clinic;
	}

	/**
	 * Setter for the clinic
	 * @param clinic of the patient
	 */
	public void setClinic(ClinicModel clinic) {
		this.clinic = clinic;
	}

	/**
	 * Getter for the drugs
	 * @return list of drugs
	 */
	public List<PatientDrugModel> getDrugs() {
		return drugs;
	}

	/** Setter for the drugs
	 * @param drugs of the patient
	 */
	public void setDrugs(List<PatientDrugModel> drugs) {
		this.drugs = drugs;
	}

	/**
	 * Getter for the appointments
	 * @return list of appointments
	 */
	public List<AppointmentModel> getAppointments() {
		return appointments;
	}

	/**
	 * Setter for the appointments
	 * @param appointments list of the patient
	 */
	public void setAppointments(List<AppointmentModel> appointments) {
		this.appointments = appointments;
	}
}
