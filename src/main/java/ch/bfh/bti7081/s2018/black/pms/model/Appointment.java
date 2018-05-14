package ch.bfh.bti7081.s2018.black.pms.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.vaadin.addon.calendar.item.BasicItem;

public class Appointment extends BasicItem {

	private LocalDate date;
	private LocalTime startTime, endTime;
	private String title;
	private String description;
	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	
	/*
	public Appointment(LocalDate date, LocalTime startTime, LocalTime endTime, String title, String description) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
		this.description = description;
		
	}
	*/
	
	public Appointment() {
			
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
	
	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
