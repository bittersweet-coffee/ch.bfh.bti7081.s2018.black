package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.LinkedList;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.LocationModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.util.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.util.JpaUtility;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;

public class PatientNewWindow extends Window {

	PatientViewImpl view;
	PatientModel patient;
	LocationModel location;
	
	public PatientNewWindow(PatientViewImpl view, PatientModel patientModel, LocationModel location) {
		super("New Patient");
		this.view = view;
		this.patient = patientModel;
		this.location = location;
		buildWindow();
	}

	private void buildWindow() {

		Label lblfirstName = new Label("First Name: ");
		Label lblLastName = new Label("Last Name: ");
		Label lblNotes = new Label("Notes: ");
		Label lblDoctors = new Label("Doctors: ");
		Label lblAppointment = new Label("Appointments: ");
		Label lblLocation = new Label("Location: ");
		

		List<DoctorModel> docList = new LinkedList<DoctorModel>();
		List<LocationModel> locList = new LinkedList<LocationModel>();

		//docList = //view.getDoctors(docList);
		//locList = //view.getLocations(locList);
				
		ComboBox<DoctorModel> cmbDocs = new ComboBox<DoctorModel>("Select your doctor:");
		cmbDocs.setItems(docList);
		cmbDocs.setItemCaptionGenerator(DoctorModel::getLastname);

		ComboBox<LocationModel> cmbLocs = new ComboBox<LocationModel>("Select a Location:");
		cmbLocs.setItems(locList);
		cmbLocs.setItemCaptionGenerator(LocationModel::getName);
		
		TwinColSelect<String> addictionselect = new TwinColSelect<>("Addictions: ");
		addictionselect.setItems("Addiction 1", "Addiction 2", "Addiction 3", "Addiction 4", "Addiction 5",
				"Addiction 6");
		addictionselect.setRows(5);

		TwinColSelect<String> drugselect = new TwinColSelect<>("Current medication: ");
		drugselect.setItems("Drug 1", "Drug 2", "Drug 3", "Drug 4", "Drug 5", "Drug 6");
		drugselect.setRows(5);

		TextField firstNameField = new TextField();
		TextField lastNameField = new TextField();
	
		TextArea descriptionField = new TextArea();
		descriptionField.setRows(5);

		Button btnSave = new Button("Save", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				patient.setFirstname(firstNameField.getValue());
				patient.setLastname(lastNameField.getValue());
				view.save(patient);
				close();
			}
		});

		Button btnAppointment = new Button("New Appointment", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TO DO
			}
		});

		Button btnDummyData = new Button("Load Dummy Data", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				firstNameField.setValue("DummyFirstName");
				lastNameField.setValue("DummyLastName");
			}
		});

		Button btnCancel = new Button("Cancel", event -> this.close());

		GridLayout tileGrid = new GridLayout(2, 14);

		tileGrid.setMargin(true);

		tileGrid.addComponent(lblfirstName, 0, 0);
		tileGrid.addComponent(firstNameField, 1, 0);
		tileGrid.addComponent(lblLastName, 0, 1);
		tileGrid.addComponent(lastNameField, 1, 1);
		tileGrid.addComponent(addictionselect, 0, 2);
		tileGrid.addComponent(lblNotes, 0, 3);
		tileGrid.addComponent(descriptionField, 0, 4);
		tileGrid.addComponent(drugselect, 0, 5);
		tileGrid.addComponent(lblDoctors, 0, 6);
		tileGrid.addComponent(cmbDocs, 1, 6);
		tileGrid.addComponent(lblAppointment, 0, 8);
		tileGrid.addComponent(btnAppointment, 1, 8);
		tileGrid.addComponent(lblLocation, 0, 9);
		tileGrid.addComponent(cmbLocs, 1, 9);
		tileGrid.addComponent(btnSave, 0, 10);
		tileGrid.addComponent(btnCancel, 1, 10);
		tileGrid.addComponent(btnDummyData, 0, 11);

		setContent(tileGrid);
	}
}
