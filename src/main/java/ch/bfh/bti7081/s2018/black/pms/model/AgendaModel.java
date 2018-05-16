package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.ArrayList;
import java.util.List;

public class AgendaModel {
	
	private List<Appointment> appointments = new ArrayList<Appointment>();
	private AppointmentDataProvider eventProvider = new AppointmentDataProvider();

	public void addAppointment(AppointmentItem appointmentItem) {
		if(!eventProvider.containsEvent(appointmentItem)) {
			eventProvider.addItem(appointmentItem);
		}
	}
	
	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public AppointmentDataProvider getEventProvider() {
		return eventProvider;
	}

	public void setEventProvider(AppointmentDataProvider eventProvider) {
		this.eventProvider = eventProvider;
	}
}
