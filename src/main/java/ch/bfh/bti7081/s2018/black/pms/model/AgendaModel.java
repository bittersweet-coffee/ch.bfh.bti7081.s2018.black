package ch.bfh.bti7081.s2018.black.pms.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class AgendaModel {
	
	private final DayOfWeek firstDayOfWeek;
	private final WeekFields weekFields;
	private final Locale germanyLocale;
	
	private TemporalField tempField;
	private LocalDate date;
	
	private int currentWeek;
	private int currentMonth;
	private int currentYear;
	
	
	public AgendaModel() {
		this.germanyLocale = Locale.GERMANY;
		this.date = LocalDate.now();
		this.weekFields = WeekFields.of(germanyLocale);
		
		this.firstDayOfWeek = this.weekFields.getFirstDayOfWeek();
		this.tempField = this.weekFields.weekOfWeekBasedYear();
		
		this.currentYear = date.getYear();
		this.currentMonth = date.getMonthValue();
		this.currentWeek = date.get(tempField);
		
		
		
	}
	
	public static void main(String [] args) {
		AgendaModel model = new AgendaModel();
		Appointment appointmentOne = new Appointment(LocalDate.of(2018, Month.MAY, 20), LocalTime.of(13, 30), LocalTime.of(15, 00), "My first Appointment");
		
		
		
		System.out.println("FirstDayOfWeek: " + model.firstDayOfWeek);
		System.out.println("Current Week: " + model.currentWeek);
		System.out.println("Current Month: " + model.currentMonth);
		System.out.println("Current Year: " + model.currentYear);
		System.out.println();
		
		System.out.println("Appointment Date: " + appointmentOne.getDateString());
		System.out.println("Appointment StartTime: " + appointmentOne.getStartTimeString());
		System.out.println("Appointment EndTime: " + appointmentOne.getEndTimeString());
		
	}
	
	
	public void nextWeek() {
		this.currentWeek ++;
	}
	
	public void prevWeek() {
		this.currentWeek --;
	}
	
	public void nextMonth() {
		this.currentMonth++;
	}
	
	public void prevMonth() {
		this.currentMonth --;
	}
	

}
