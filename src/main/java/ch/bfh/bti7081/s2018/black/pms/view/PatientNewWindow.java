package ch.bfh.bti7081.s2018.black.pms.view;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;

public class PatientNewWindow extends Window {

	PatientViewImpl view;
	PatientItem patient;
	List<String> dataAppointment;
	TextArea appointmentsList;
	
	public PatientNewWindow(PatientViewImpl view, PatientItem patientItem) {
		super("New Patient");
		this.view = view;
		this.patient = patientItem;
		this.dataAppointment = new LinkedList<String>();
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
		Label lblLocation = new Label("Location: ");
		Label lblBirthday = new Label("Birthday: ");
	
		
		TwinColSelect<String> addictionselect = new TwinColSelect<>("Addictions: ");
		addictionselect.setItems(Controller.getAddictionNames(Controller.getAddictions()));
		addictionselect.setRows(5);


		TextField firstNameField = new TextField();
		TextField lastNameField = new TextField();
		TextField phoneField = new TextField();
		TextField postodeField = new TextField();
		TextField streetField = new TextField();
		DateField birthdayField = new DateField();
			
		
	
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
				Date birthdayUnformatted = Date.from(birthdayField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				patient.setBirthday(birthdayUnformatted);
				patient.setDoctors(Controller.getSelectedDoctor());
				patient.setLocation(Controller.getSelectedLocation());
				patient.setAddictions(Controller.parseSelectedAddictions(addictionselect.getSelectedItems()));
				view.save(patient, descriptionField.getValue());
				Page.getCurrent().reload();
				close();
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
		tileGrid.addComponent(lblBirthday, 0, 2);
		tileGrid.addComponent(birthdayField, 1, 2);
		tileGrid.addComponent(lblStreet, 0, 3);
		tileGrid.addComponent(streetField, 1, 3);
		tileGrid.addComponent(lblpostCode, 0, 4);
		tileGrid.addComponent(postodeField, 1, 4);
		tileGrid.addComponent(lblPhone, 0, 5);
		tileGrid.addComponent(phoneField, 1, 5);
		tileGrid.addComponent(lblDoctors, 0, 6);
		Controller.setDoctorCombobox(tileGrid, 1, 6);
		tileGrid.addComponent(lblLocation, 0, 7);
		Controller.setLocationCombobox(tileGrid, 1, 7);
		
		VerticalLayout rightComponentBox = new VerticalLayout(addictionselect, lblNotes, descriptionField);
		HorizontalLayout navigationButtons = new HorizontalLayout(btnSave, btnDummyData, btnCancel);
		VerticalLayout leftComponentBox = new VerticalLayout(tileGrid, navigationButtons);
		HorizontalLayout mainOpenWindow = new HorizontalLayout(leftComponentBox, rightComponentBox);
		this.setWidth("1000px");
		tileGrid.setMargin(true);
		
		setContent(mainOpenWindow);
	}
	
	public PatientNewWindow getPatientNewWindow() {
		return this;
	}
}
