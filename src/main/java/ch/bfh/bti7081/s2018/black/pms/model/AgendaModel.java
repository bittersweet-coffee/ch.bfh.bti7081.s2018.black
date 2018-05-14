package ch.bfh.bti7081.s2018.black.pms.model;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

import org.vaadin.addon.calendar.Calendar;
import org.vaadin.addon.calendar.handler.BasicDateClickHandler;
import org.vaadin.addon.calendar.ui.CalendarComponentEvents;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class AgendaModel {
	
	private final DayOfWeek firstDayOfWeek;
	private final WeekFields weekFields;
	private final Locale germanyLocale;
	
	private TemporalField tempField;
	private LocalDate date;
	
	private int currentWeek;
	private int currentMonth;
	private int currentYear;
	
	
    private AppointmentDataProvider eventProvider;

    private Calendar<AppointmentItem> agenda;
	
	
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
	
	/*
	public static void main(String [] args) {
		AgendaModel model = new AgendaModel();
		Appointment appointmentOne = new Appointment(LocalDate.of(2018, Month.MAY, 20), LocalTime.of(13, 30), LocalTime.of(15, 00), "My first Appointment","My first Description");
		
		
		
		System.out.println("FirstDayOfWeek: " + model.firstDayOfWeek);
		System.out.println("Current Week: " + model.currentWeek);
		System.out.println("Current Month: " + model.currentMonth);
		System.out.println("Current Year: " + model.currentYear);
		System.out.println();
		
		System.out.println("Appointment Date: " + appointmentOne.getDateString());
		System.out.println("Appointment StartTime: " + appointmentOne.getStartTimeString());
		System.out.println("Appointment EndTime: " + appointmentOne.getEndTimeString());
		
	}
	*/
	
	public Calendar<AppointmentItem> getAgendaModel() {
        return this.agenda;
    }
	
	private void initAgenda() {
		
		this.eventProvider = new AppointmentDataProvider();
        this.agenda = new Calendar<>(eventProvider);
		
        this.addAgendaEventListeners();
        this.setupNonWorkingHours();
	}
	
    private void onAgendaClick(CalendarComponentEvents.ItemClickEvent event) {

    	AppointmentItem appItem = (AppointmentItem) event.getCalendarItem();

        final Appointment appointment = appItem.getAppointment();

        Notification.show(appointment.getTitle(), appointment.getDescription(), Type.HUMANIZED_MESSAGE);
    }
	
    
    private void onAgendaRangeSelect(CalendarComponentEvents.RangeSelectEvent event) {

    	Appointment appointment = new Appointment();

        appointment.setStart(event.getStart());
        appointment.setEnd(event.getEnd());

        eventProvider.addItem(new AppointmentItem(appointment));
	}
	
    private void addAgendaEventListeners() {
        agenda.setHandler(new BasicDateClickHandler(true));
        agenda.setHandler(this::onAgendaClick);
        agenda.setHandler(this::onAgendaRangeSelect);
    }
    
    private void setupNonWorkingHours() {
    	
    	LocalTime tEnd = LocalTime.of(7, 30, 0);
    	
    	long start = 0l;
    	long end = tEnd.toSecondOfDay()*1000;
    	
    	agenda.addTimeBlock(start, end, "my-blocky-style");
    	
    	
    	LocalTime tStart = LocalTime.of(12, 0);
    	tEnd = LocalTime.of(13, 30);
    	
    	start = tStart.toSecondOfDay()*1000;
    	end = tEnd.toSecondOfDay()*1000;
    	
    	agenda.addTimeBlock(start, end);
    	
    	
    	tStart = LocalTime.of(17, 30);
    	tEnd = LocalTime.of(24, 0);
    	
    	agenda.addTimeBlock(start, end);
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
	
	public void nextYear() {
		this.currentYear++;
	}
	
	public void prevYear() {
		this.currentYear --;
	}
	

}
