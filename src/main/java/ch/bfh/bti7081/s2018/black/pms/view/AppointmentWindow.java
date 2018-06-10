package ch.bfh.bti7081.s2018.black.pms.view;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2018.black.pms.model.AppointmentItem;

public class AppointmentWindow extends Window {
	
	View view;

	public AppointmentWindow(View view, AppointmentItem appointmentItem) {
		super("New Appointment");
		this.view = view;
		buildWindow(appointmentItem);
	}
	
	private void buildWindow(AppointmentItem appointmentItem) {
		setWidth(500.0f, Unit.PIXELS);

		TextField titleField = new TextField();
		if (appointmentItem.getCaption() != null) {
			titleField.setValue(appointmentItem.getAppointment().getTitle());
		}
		TextArea descriptionField = new TextArea();
		if (appointmentItem.getDescription() != null) {
			descriptionField.setValue(appointmentItem.getAppointment().getDescription());
		}
		descriptionField.setRows(5);
		
		DateTimeField startDateTimeField = new DateTimeField();
		DateTimeField endDateTimeField = new DateTimeField();
		startDateTimeField.setValue(appointmentItem.getAppointment().getStart());
		endDateTimeField.setValue(appointmentItem.getAppointment().getEnd());
		
		Label startDatePanel = new Label("Start Date");
		Label endDatePanel = new Label("End Date");
		Label namePanel = new Label("Name");
		Label descriptionPanel = new Label("Description");
		Button btnSave = new Button("Save", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				appointmentItem.setCaption(titleField.getValue());
				appointmentItem.setDescription(descriptionField.getValue());
				appointmentItem.setStart(ZonedDateTime.of(startDateTimeField.getValue(), ZoneId.systemDefault()));
				appointmentItem.setEnd(ZonedDateTime.of(endDateTimeField.getValue(), ZoneId.systemDefault()));
				if (view.equals(AgendaViewImpl.class)) {
					((AgendaViewImpl) view).saveAppointment(appointmentItem);
				} else if (view.equals(PatientNewWindow.class)) {
					((PatientNewWindow) view).saveAppointment(appointmentItem);
				}
				
				close();
			}
		});
		Button btnDelete = new Button("Delete", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (view.equals(AgendaViewImpl.class)) {
					((AgendaViewImpl) view).deleteAppointment(appointmentItem);
				} 
				close();
			}
		});
		Button btnCancel = new Button("Cancel", event -> this.close());
		
		GridLayout tileGrid = new GridLayout(3, 5);
		
		tileGrid.setMargin(true);
        tileGrid.addComponent(startDatePanel, 0, 0);
        tileGrid.addComponent(endDatePanel, 0, 1);
        tileGrid.addComponent(namePanel, 0, 2);
        tileGrid.addComponent(descriptionPanel, 0, 3);
        tileGrid.addComponent(btnSave, 0, 4); 
        tileGrid.addComponent(startDateTimeField, 1, 0);
		tileGrid.addComponent(endDateTimeField, 1, 1);
		tileGrid.addComponent(titleField, 1, 2);
		tileGrid.addComponent(descriptionField, 1, 3);
		tileGrid.addComponent(btnCancel, 1, 4); 
		tileGrid.addComponent(btnDelete, 2, 4); 
		
        setContent(tileGrid);
	}
}
