package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2018.black.pms.controller.Controller;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.view.PatientView.PatientViewListener;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PatientNewWindow extends Window {

	PatientViewImpl view;
	PatientItem patient;
	private List<PatientViewListener> listeners;
	
	public PatientNewWindow(PatientViewImpl view, PatientItem patientItem, List<PatientViewListener> listeners) {
		super("New Patient");
		this.view = view;
		this.patient = patientItem;
		this.listeners = listeners;
		buildWindow();
	}

	private void buildWindow() {

		Label lblfirstName = new Label("First Name: ");
		Label lblLastName = new Label("Last Name: ");
		Label lblStreet = new Label("Street: ");
		Label lblpostCode = new Label("Post Code: ");
		Label lblPhone = new Label("Phone: ");
		
		Label lblNotes = new Label("Notes: ");
		Label lblDoctors = new Label("Doctors: ");
		Label lblAppointment = new Label("Appointments: ");
		Label lblLocation = new Label("Location: ");
	
		
		TwinColSelect<String> addictionselect = new TwinColSelect<>("Addictions: ");
		addictionselect.setItems(Controller.getAddictionNames(Controller.getAddictions(listeners)));
		addictionselect.setRows(5);

		TwinColSelect<String> drugselect = new TwinColSelect<>("Current medication: ");
		drugselect.setItems(Controller.getDrugNames(Controller.getDrugs(listeners)));
		drugselect.setRows(5);

		TextField firstNameField = new TextField();
		TextField lastNameField = new TextField();
		TextField phoneField = new TextField();
		TextField postodeField = new TextField();
		TextField streetField = new TextField();
		
	
		TextArea descriptionField = new TextArea();
		descriptionField.setRows(5);

		Button btnSave = new Button("Save", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				patient.setFirstName(firstNameField.getValue());
				patient.setLastName(lastNameField.getValue());
				patient.setStreet(streetField.getValue());
				patient.setPostcode(Integer.parseInt(postodeField.getValue())); //TO-DO: Check if int!!
				patient.setTelephone(phoneField.getValue());
				patient.setDoctors(Controller.getSelectedDoctor());
				patient.setLocation(Controller.getSelectedLocation());
				patient.setDrugs(Controller.parseSelectedDrugs(drugselect.getSelectedItems(), listeners));
				patient.setAddictions(Controller.parseSelectedAddictions(addictionselect.getSelectedItems(), listeners));
				view.save(patient, descriptionField.getValue());
				Page.getCurrent().reload();
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
				streetField.setValue("Dummy Street 1");
				postodeField.setValue("1234");
				phoneField.setValue("Dummy Phone 123");
				
			}
		});

		Button btnCancel = new Button("Cancel", event -> this.close());

		GridLayout tileGrid = new GridLayout(2, 8);
		tileGrid.addComponent(lblfirstName, 0, 0);
		tileGrid.addComponent(firstNameField, 1, 0);
		tileGrid.addComponent(lblLastName, 0, 1);
		tileGrid.addComponent(lastNameField, 1, 1);
		tileGrid.addComponent(lblStreet, 0, 2);
		tileGrid.addComponent(streetField, 1, 2);
		tileGrid.addComponent(lblpostCode, 0, 3);
		tileGrid.addComponent(postodeField, 1, 3);
		tileGrid.addComponent(lblPhone, 0, 4);
		tileGrid.addComponent(phoneField, 1, 4);
		tileGrid.addComponent(lblDoctors, 0, 5);
		Controller.setDoctorCombobox(tileGrid, listeners, 1, 5);
		tileGrid.addComponent(lblLocation, 0, 6);
		Controller.setLocationCombobox(tileGrid, listeners, 1, 6);
		tileGrid.addComponent(lblAppointment, 0, 7);
		tileGrid.addComponent(btnAppointment, 1, 7);
		
		VerticalLayout rightComponentBox = new VerticalLayout(addictionselect, lblNotes, descriptionField, drugselect);
		HorizontalLayout navigationButtons = new HorizontalLayout(btnSave, btnDummyData, btnCancel);
		VerticalLayout leftComponentBox = new VerticalLayout(tileGrid, navigationButtons);
		HorizontalLayout mainOpenWindow = new HorizontalLayout(leftComponentBox, rightComponentBox);
		this.setWidth("1000px");
		tileGrid.setMargin(true);
		
		setContent(mainOpenWindow);
	}

}
