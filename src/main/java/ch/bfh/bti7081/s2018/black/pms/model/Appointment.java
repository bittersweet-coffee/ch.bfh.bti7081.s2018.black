package ch.bfh.bti7081.s2018.black.pms.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="appointment")
public class Appointment extends EntityModel{

	private String name;

	private String description;

	private LocalDateTime start;

	private LocalDateTime end;
	
	private String period;
	
	@Transient
	private DateTimeFormatter dateFormatter;
	@Transient
	private DateTimeFormatter timeFormatter;
	
	@ManyToMany(mappedBy="appointments")
	private List<PatientModel> patients;
	
	@ManyToMany(mappedBy="appointments")
	private List<DoctorModel> doctors;
	
	@ManyToOne
	@JoinColumn(name="location_id", nullable=false)
	private LocationModel location;

	public Appointment() {
	}
	
	public Appointment(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return this.description;
	}	

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public List<PatientModel> getPatients() {
		return patients;
	}

	public void setPatients(List<PatientModel> patients) {
		this.patients = patients;
	}

	public List<DoctorModel> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<DoctorModel> doctors) {
		this.doctors = doctors;
	}

	public LocationModel getLocation() {
		return location;
	}

	public void setLocation(LocationModel location) {
		this.location = location;
	}
}
