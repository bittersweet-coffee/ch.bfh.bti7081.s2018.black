package ch.bfh.bti7081.s2018.black.pms.view;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2018.black.pms.model.AppointmentItem;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
/**
 * AppointmentWindow Class
 * @author bielc1
 *
 */
public class AppointmentWindow extends Window {
	
	AgendaViewImpl view;
	// List containing Mock Objects for the PatientModel
	private List<PatientItem> patientItemList;
	// DataProvide for the ComboBox
	private ListDataProvider<PatientItem> patientProvider;
	
	// List containing Mock Objects for the PatientModel
	private List<DoctorItem> doctorItemList;
	// DataProvide for the ComboBox
	private ListDataProvider<DoctorItem> doctorProvider;
	
	ComboBox<PatientItem> comboBoxPatient;
	ComboBox<DoctorItem> comboBoxDoctor;

	public AppointmentWindow(AgendaViewImpl view, AppointmentItem appointmentItem, List<PatientItem> patientItemList, List<DoctorItem> doctorItemList) {
		super("New Appointment");
		this.view = view;
		this.patientItemList = patientItemList;
		this.doctorItemList = doctorItemList;
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
		Label patientPanel = new Label("Patient");
		Label doctorPanel = new Label("Doctor");
		Button btnSave = new Button("Save", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				appointmentItem.setCaption(titleField.getValue());
				appointmentItem.setDescription(descriptionField.getValue());
				appointmentItem.setStart(ZonedDateTime.of(startDateTimeField.getValue(), ZoneId.systemDefault()));
				appointmentItem.setEnd(ZonedDateTime.of(endDateTimeField.getValue(), ZoneId.systemDefault()));
				appointmentItem.getAppointment().setPatientItem(comboBoxPatient.getValue());
				appointmentItem.getAppointment().setDoctorItem(comboBoxDoctor.getValue());
				if (view != null) {
					((AgendaViewImpl) view).saveAppointment(appointmentItem);
				}
				close();
			}
		});
		Button btnDelete = new Button("Delete", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (view.getClass().equals(AgendaViewImpl.class)) {
					((AgendaViewImpl) view).deleteAppointment(appointmentItem);
				} 
				close();
			}
		});
		Button btnCancel = new Button("Cancel", event -> this.close());
		
		patientProvider = DataProvider.ofCollection(patientItemList);
		patientProvider.refreshAll();
		patientProvider.withConfigurableFilter();
		
		doctorProvider = DataProvider.ofCollection(doctorItemList);
		doctorProvider.refreshAll();
		doctorProvider.withConfigurableFilter();
		
		comboBoxPatient = new ComboBox<>();
		comboBoxPatient.setDataProvider(patientProvider);
		comboBoxPatient.setItemCaptionGenerator(PatientItem::getFullName);
		if(appointmentItem.getAppointment().getPatientItem() != null) {
			comboBoxPatient.setSelectedItem(appointmentItem.getAppointment().getPatientItem());
		}
		comboBoxDoctor = new ComboBox<>();
		comboBoxDoctor.setDataProvider(doctorProvider);
		comboBoxDoctor.setItemCaptionGenerator(DoctorItem::getFullName);
		if(appointmentItem.getAppointment().getDoctorItem() != null) {
			comboBoxDoctor.setSelectedItem(appointmentItem.getAppointment().getDoctorItem());
		}
		
		GridLayout tileGrid = new GridLayout(3, 7);
		
		tileGrid.setMargin(true);
        tileGrid.addComponent(startDatePanel, 0, 0);
        tileGrid.addComponent(endDatePanel, 0, 1);
        tileGrid.addComponent(namePanel, 0, 2);
        tileGrid.addComponent(descriptionPanel, 0, 3);
        tileGrid.addComponent(patientPanel, 0, 4);
        tileGrid.addComponent(doctorPanel, 0, 5);
        tileGrid.addComponent(btnSave, 0, 6); 
        tileGrid.addComponent(startDateTimeField, 1, 0);
		tileGrid.addComponent(endDateTimeField, 1, 1);
		tileGrid.addComponent(titleField, 1, 2);
		tileGrid.addComponent(descriptionField, 1, 3);
		tileGrid.addComponent(comboBoxPatient, 1, 4);
		tileGrid.addComponent(comboBoxDoctor, 1, 5);
		tileGrid.addComponent(btnCancel, 1, 6); 
		tileGrid.addComponent(btnDelete, 2, 6); 
		
        setContent(tileGrid);
	}
}
