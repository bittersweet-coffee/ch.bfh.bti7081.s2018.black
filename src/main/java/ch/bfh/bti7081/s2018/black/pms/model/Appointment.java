package ch.bfh.bti7081.s2018.black.pms.model;

import java.time.LocalDateTime;

public class Appointment {

	private int id;
	private LocalDateTime start;
	private LocalDateTime end;
	private String title;
	private String description;
	
	private DoctorItem doctorItem;
	private PatientItem patientItem;
	
	public Appointment() {
	}
	
	public Appointment(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}

	public LocalDateTime getStart() {
		return this.start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return this.end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DoctorItem getDoctorItem() {
		return doctorItem;
	}

	public void setDoctorItem(DoctorItem doctorItem) {
		this.doctorItem = doctorItem;
	}

	public PatientItem getPatientItem() {
		return patientItem;
	}

	public void setPatientItem(PatientItem patientItem) {
		this.patientItem = patientItem;
	}
	
}
