package ch.bfh.bti7081.s2018.black.pms.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.vaadin.addon.calendar.item.BasicItem;

public class Appointment {

	private LocalDateTime start;
	private LocalDateTime end;
	private String title;
	private String description;
	
	public Appointment() {
		
	}
	
	public Appointment(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
