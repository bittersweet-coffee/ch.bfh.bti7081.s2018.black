package ch.bfh.bti7081.s2018.black.pms.view;


import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.AppointmentDataProvider;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentItem;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

public interface AgendaView {
	
	public interface AgendaViewListener {
		void saveAppointment(AppointmentItem appointmentItem);
		void deleteAppointment(AppointmentItem appointmentItem);
		List<PatientItem> setupPatientItemList();
		List<DoctorItem> setupDoctorItemList();
	}
	public void addListener(AgendaViewListener listener);
	public void addEventProvider(AppointmentDataProvider eventProvider);
}
