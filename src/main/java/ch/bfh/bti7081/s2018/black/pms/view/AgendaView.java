package ch.bfh.bti7081.s2018.black.pms.view;


import ch.bfh.bti7081.s2018.black.pms.model.AppointmentDataProvider;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentItem;

public interface AgendaView {
	
	public interface AgendaViewListener {
		void saveAppointment(AppointmentItem appointmentItem);
		void deleteAppointment(AppointmentItem appointmentItem);
		//void setCalendarRange(LocalDateTime start, LocalDateTime end);
	}
	public void addListener(AgendaViewListener listener);
	public void addEventProvider(AppointmentDataProvider eventProvider);
}
