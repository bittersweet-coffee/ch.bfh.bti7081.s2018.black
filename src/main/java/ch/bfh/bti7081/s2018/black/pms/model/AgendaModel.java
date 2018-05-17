package ch.bfh.bti7081.s2018.black.pms.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AgendaModel {
	
	private List<Appointment> appointments = new ArrayList<Appointment>();
	private AppointmentDataProvider eventProvider = new AppointmentDataProvider();

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
	
	public void nextWeek() {
		this.currentWeek ++;
	}
	
	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public void addAppointment(AppointmentItem appointmentItem) {
		if(!eventProvider.containsEvent(appointmentItem)) {
			eventProvider.addItem(appointmentItem);
		}
	}

	public AppointmentDataProvider getEventProvider() {
		return eventProvider;
	}

	public void setEventProvider(AppointmentDataProvider eventProvider) {
		this.eventProvider = eventProvider;
	}
}
