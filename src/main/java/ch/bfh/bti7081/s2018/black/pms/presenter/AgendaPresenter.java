package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2018.black.pms.model.AgendaModel;
import ch.bfh.bti7081.s2018.black.pms.model.Appointment;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentItem;
import ch.bfh.bti7081.s2018.black.pms.view.AgendaView;
import ch.bfh.bti7081.s2018.black.pms.view.DashboardView;

public class AgendaPresenter implements AgendaView.AgendaViewListener {

	private AgendaView view;
	private AgendaModel model;
	
	public AgendaPresenter(AgendaView view, AgendaModel model) {
		this.view = view;
		this.model = model;
		view.addListener(this);
		view.addEventProvider(model.getEventProvider());
	}

	@Override
	public void buttonClick() {

	}

	@Override
	public void saveButtonClick(AppointmentItem appointmentItem) {
		model.addAppointment(appointmentItem);
	}
	
	@Override
	public void newAppointment(AppointmentItem appointmentItem) {
		model.addAppointment(appointmentItem);
	}

}
