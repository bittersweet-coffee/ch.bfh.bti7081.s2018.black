package ch.bfh.bti7081.s2018.black.pms.view;


import ch.bfh.bti7081.s2018.black.pms.model.AppointmentDataProvider;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentItem;

public interface AgendaView {
	
	public interface AgendaViewListener {
		void buttonClick();
		void saveButtonClick(AppointmentItem appointmentItem);
		void newAppointment(AppointmentItem appointmentItem);
	}
	public void addListener(AgendaViewListener listener);
	public void addEventProvider(AppointmentDataProvider eventProvider);
}
