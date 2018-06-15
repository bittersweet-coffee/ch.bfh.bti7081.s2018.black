package ch.bfh.bti7081.s2018.black.pms.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Agenda class
 * Implements the overall agenda consisting of multiple appointments.
 */
public class AgendaModel {
	
	// List of appointments
	private List<AppointmentModel> appointments = new ArrayList<AppointmentModel>();
	private AppointmentDataProvider eventProvider = new AppointmentDataProvider();

	// Date variables and fields
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
	
	/**
	 * Increment the currentWeek
	 */
	public void nextWeek() {
		this.currentWeek ++;
	}
	
	/**
	 * Getter for appointments
	 * @return the list of appointments
	 */
	public List<AppointmentModel> getAppointments() {
		return appointments;
	}

	/**
	 * Setter for the appointments
	 * @param list of appointments
	 */
	public void setAppointments(List<AppointmentModel> appointments) {
		this.appointments = appointments;
	}

	/**
	 * Add appointment item
	 * @param the appointment item
	 */
	public void addAppointment(AppointmentItem appointmentItem) {
		if (!eventProvider.containsEvent(appointmentItem)) {
			eventProvider.addItem(appointmentItem);
		}
	}

	/**
	 * Getter for the event provider
	 * @return the appointment data provider
	 */
	public AppointmentDataProvider getEventProvider() {
		return eventProvider;
	}

	/**
	 * Setter for the event provider
	 * @param the event provider
	 */
	public void setEventProvider(AppointmentDataProvider eventProvider) {
		this.eventProvider = eventProvider;
	}
}
