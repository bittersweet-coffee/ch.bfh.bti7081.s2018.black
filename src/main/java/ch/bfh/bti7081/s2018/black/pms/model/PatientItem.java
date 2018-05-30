package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;

public class PatientItem {
	
	private Integer id;
	
	private List<String> notes;
	
	private String firstName, lastName;
	
	public PatientItem(Integer id, String firstName, String lastName, List<String> notes) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.notes = notes;
		
		
		
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getNotes() {
		return notes;
	}
	
	public String getNotesAsString() {
		String strNotes = "";
		for(String note : this.notes) {
			strNotes = strNotes.concat(note + "<br><br>");
		}
		if(strNotes.length() > 2)  // cut the ending line feeds
			return strNotes.substring(0, strNotes.length()-2);
			
		return strNotes;
	}

	public void setNotes(List<String> notes) {
		this.notes = notes;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
