package ch.bfh.bti7081.s2018.black.pms.view;


import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.AppointmentDataProvider;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentItem;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
/**
 * AgendaView Interface
 * View Interface used by the ViewImplementation
 * @author bielc1
 *
 */
public interface AgendaView {
	/**
	 * AgendaViewListener Interface
	 * ViewListener Interface used by the Presenter Class
	 * @author beilc1
	 *
	 */
	public interface AgendaViewListener {
		/**
		 * Method used to tell the Listener to save an appointment
		 * @param appointmentItem The mock object of the Appointment (AppointmentItem)
		 */
		void saveAppointment(AppointmentItem appointmentItem);
		/**
		 * Method used to tell the Listener to delete an appointment
		 * @param appointmentItem The mock object of the Appointment (AppointmentItem)
		 */
		void deleteAppointment(AppointmentItem appointmentItem);
		/**
		 * Method used to tell the Listener to setup the List with all PatientItems.
		 * @return List of PatientItems containing mock objects of the PatientModel (PatientItem)
		 */
		List<PatientItem> setupPatientItemList();
		/**
		 * Method used to tell the Listener to setup the List with all DoctorItems.
		 * @return List of DoctorsItems containing mock objects of the DoctorModel (DoctorItem)
		 */
		List<DoctorItem> setupDoctorItemList();
	}
	/**
	 * Method used by the Presenter to register itself as listener
	 * @param listener instance of the corresponding presenter
	 */
	public void addListener(AgendaViewListener listener);
	/**
	 * Method used by the Presenter to add an Event Provider. The event Provider is used to handle 
	 * the appointments on the calendar
	 * @param eventProvider
	 */
	public void addEventProvider(AppointmentDataProvider eventProvider);
}
