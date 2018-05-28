package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.Appointment;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentDataProvider;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentItem;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentModel;
import ch.bfh.bti7081.s2018.black.pms.util.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.util.JpaUtility;
import ch.bfh.bti7081.s2018.black.pms.view.AgendaView;

public class AgendaPresenter implements AgendaView.AgendaViewListener {

	private AgendaView view;
	
	private AppointmentDataProvider eventProvider = new AppointmentDataProvider();
	private List<AppointmentModel> appointmentModelList;
	
	private LocalDateTime start = LocalDateTime.now();
	private LocalDateTime end = LocalDateTime.now();
	
	public AgendaPresenter(AgendaView view) {
		this.view = view;
		this.view.addListener(this);
		this.view.addEventProvider(eventProvider);
		appointmentModelList = new LinkedList<AppointmentModel>();
		this.fillAppointmentList();
	}

	@Override
	public void saveAppointment(AppointmentItem appointmentItem) {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		if(appointmentItem.getAppointment().getId() == 0) {
			eventProvider.addItem(appointmentItem);
			AppointmentModel appointmentModel = new AppointmentModel();
			appointmentModel.setName(appointmentItem.getAppointment().getTitle());
			appointmentModel.setDescription(appointmentItem.getAppointment().getDescription());
			appointmentModel.setStart(appointmentItem.getAppointment().getStart());
			appointmentModel.setEnd(appointmentItem.getAppointment().getEnd());
			objects.store(appointmentModel);
			appointmentItem.getAppointment().setId(objects.getLastId());
			appointmentModelList.add(appointmentModel);
		}else {
			for (AppointmentModel appointmentModel : this.appointmentModelList) {
				if(appointmentModel.getId() == appointmentItem.getAppointment().getId()) {
					appointmentModel.setName(appointmentItem.getAppointment().getTitle());
					appointmentModel.setDescription(appointmentItem.getAppointment().getDescription());
					appointmentModel.setStart(appointmentItem.getAppointment().getStart());
					appointmentModel.setEnd(appointmentItem.getAppointment().getEnd());
					objects.update(appointmentModel);
				}
			}
		}
	}
	
	@Override
	public void deleteAppointment(AppointmentItem appointmentItem) {
		eventProvider.removeItem(appointmentItem);
		if (appointmentItem.getAppointment().getId() != 0) {
			removeAppointment(appointmentItem.getAppointment());
		}
	}
	
	private void removeAppointment(Appointment appointment) {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		for (AppointmentModel appointmentModel : this.appointmentModelList) {
			appointmentModel.setName(appointment.getTitle());
			appointmentModel.setDescription(appointment.getDescription());
			appointmentModel.setStart(appointment.getStart());
			appointmentModel.setEnd(appointment.getEnd());
			objects.remove(appointmentModel);
     	}
	}
	
	public void fillAppointmentList() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.appointmentModelList = objects.findAll2(AppointmentModel.class);
		for (AppointmentModel appointmentModel : this.appointmentModelList) {
			Appointment appointment = new Appointment();
			appointment.setId(appointmentModel.getId());
			appointment.setTitle(appointmentModel.getName());
			appointment.setDescription(appointmentModel.getDescription());
			appointment.setStart(appointmentModel.getStart());
			appointment.setEnd(appointmentModel.getEnd());
			eventProvider.addItem(new AppointmentItem(appointment));
     	}
	}
}
