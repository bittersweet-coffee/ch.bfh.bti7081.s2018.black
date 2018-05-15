package ch.bfh.bti7081.s2018.black.pms.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;

	private String description;

	private LocalDate date;
	
	@Column(name="start_time")
	private LocalTime startTime; 
	
	@Column(name="end_time")
	private LocalTime endTime;
	
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
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return this.date;
	}
	
	public String getDateString() {
		return dateFormatter.format(this.date);
		
	}

	public String getStartTimeString() {
		return timeFormatter.format(this.startTime);
	}
	
	public String getEndTimeString() {
		return timeFormatter.format(this.endTime);
	}
	
	
	public LocalTime getStartTime() {
		return this.startTime;
	}


	public LocalTime getEndTime() {
		return this.endTime;
	}


	public String getDescription() {
		return this.description;
	}
	
}
