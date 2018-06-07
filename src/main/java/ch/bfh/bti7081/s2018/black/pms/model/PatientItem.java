package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PatientItem {
	
	private Integer id;
	
	private List<String> notes;
	private PatientModel model;
	
	private String firstName, lastName;
	private Date birthday;
	
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
	
	public Date getBirthday() {
		return this.birthday;
	}

	public String getBirthdayAsString() {
		return String.valueOf(this.birthday);
	}
	
	public PatientModel getModel() {
		return this.model;
	}
}
