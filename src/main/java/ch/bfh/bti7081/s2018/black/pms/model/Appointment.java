package ch.bfh.bti7081.s2018.black.pms.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Appointment {

	private LocalDate date;
	private LocalTime startTime, endTime;
	private String description;
	private DateTimeFormatter dateFormatter;
	private DateTimeFormatter timeFormatter;
	
	public Appointment(LocalDate date, LocalTime startTime, LocalTime endTime, String description) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		
		this.dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		
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
