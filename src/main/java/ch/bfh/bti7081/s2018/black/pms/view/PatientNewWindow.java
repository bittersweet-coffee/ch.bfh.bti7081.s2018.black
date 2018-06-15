package ch.bfh.bti7081.s2018.black.pms.view;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;

/**
 * PatientNewWindow
 */
public class PatientNewWindow extends Window {

	PatientViewImpl view;
	PatientItem patient;
	List<String> dataAppointment;
	TextArea appointmentsList;

	final static Logger logger = Logger.getLogger(PatientNewWindow.class);
	
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
		addictionselect.setItems(view.getAddictionNames());
		addictionselect.setRows(5);

		TextField firstNameField = new TextField();
		firstNameField.setRequiredIndicatorVisible(true);
		TextField lastNameField = new TextField();
		lastNameField.setRequiredIndicatorVisible(true);
		TextField phoneField = new TextField();
		phoneField.setRequiredIndicatorVisible(true);
		TextField postCodeField = new TextField();
		postCodeField.setRequiredIndicatorVisible(true);
		TextField streetField = new TextField();
		streetField.setRequiredIndicatorVisible(true);
		DateField birthdayField = new DateField();
		birthdayField.setRequiredIndicatorVisible(true);
		
		ComboBox<String> cmbLocs = new ComboBox<String>("Select a Location:");
		List<String> locNameList = view.getClinicNames();
		cmbLocs.setItems(locNameList);
		cmbLocs.setItemCaptionGenerator(String::toString);
		cmbLocs.setRequiredIndicatorVisible(true);
		ComboBox<String> cmbDocs = new ComboBox<String>("Select a doctor:");
		List<String> docNameList = view.getDocNames();
		cmbDocs.setItems(docNameList);
		cmbDocs.setItemCaptionGenerator(String::toString);
		cmbDocs.setRequiredIndicatorVisible(true);
		
		TextArea descriptionField = new TextArea();
		descriptionField.setRows(5);

		Button btnSave = new Button("Save", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				
				if (firstNameField.isEmpty() || lastNameField.isEmpty() || phoneField.isEmpty() 
						|| postCodeField.isEmpty() || streetField.isEmpty() || birthdayField.isEmpty()
						|| !cmbLocs.getSelectedItem().isPresent() || !cmbDocs.getSelectedItem().isPresent()) {
					Notification.show("Warning", "Please fill out all required fields!", Notification.Type.ERROR_MESSAGE);
				} else {
					
					patient.setFirstName(firstNameField.getValue());
					patient.setLastName(lastNameField.getValue());
					patient.setStreet(streetField.getValue());
					
					patient.setTelephone(phoneField.getValue());
					Optional<String> doc = cmbDocs.getSelectedItem();
					if (doc.isPresent()) {
						view.getSelectedDoctor(doc, patient);
					} else {
						Notification.show("Warning", "No Doctor was selected!", Notification.Type.ERROR_MESSAGE);
					}
					Optional<String> clinic = cmbLocs.getSelectedItem();
					if (clinic.isPresent()) {
						view.getSelectedClinic(clinic, patient);
					} else {
						Notification.show("Warning", "No Clinic was selected!", Notification.Type.ERROR_MESSAGE);
					}
					
					view.setSelectedAddictions(addictionselect.getSelectedItems(), patient);
					
					if (isValidDate(birthdayField.getValue()) && isValidPLZ(postCodeField.getValue())) {
						patient.setBirthday(birthdayField.getValue());
						patient.setPostcode(Integer.parseInt(postCodeField.getValue()));
						
						view.save(patient, descriptionField.getValue());
						close();
						Page.getCurrent().reload();
						
					} else if(!isValidDate(birthdayField.getValue())) {
						Notification.show("Warning", "Birthday is not valid! Please check the selected Date.", Notification.TYPE_ERROR_MESSAGE);
					
					} else {
						Notification.show("Warning", "Post Code is not valid! Please enter a 4-digit Post Code.", Notification.TYPE_ERROR_MESSAGE);
					}
				}				
			}

			private boolean isValidDate(LocalDate localDate) {
				if (localDate.isAfter(LocalDate.now())) {
					return false;
				} else {
					return true;
				}
			}
			
			private boolean isValidPLZ(String str) {
				try {
					Integer.parseInt(postCodeField.getValue());
					
					if (str.length() != 4) {
						throw new NumberFormatException();
					}
					
					return true;
					
				} catch (Exception e) {
					logger.error(e);
					return false;
				}
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
		tileGrid.addComponent(postCodeField, 1, 4);
		tileGrid.addComponent(lblPhone, 0, 5);
		tileGrid.addComponent(phoneField, 1, 5);
		tileGrid.addComponent(lblDoctors, 0, 6);
		tileGrid.addComponent(cmbDocs, 1, 6);
		tileGrid.addComponent(lblLocation, 0, 7);
		tileGrid.addComponent(cmbLocs, 1, 7);
		
		VerticalLayout rightComponentBox = new VerticalLayout(addictionselect, lblNotes, descriptionField);
		HorizontalLayout navigationButtons = new HorizontalLayout(btnSave, btnCancel);
		VerticalLayout leftComponentBox = new VerticalLayout(tileGrid, navigationButtons);
		HorizontalLayout mainOpenWindow = new HorizontalLayout(leftComponentBox, rightComponentBox);
		this.setWidth("1000px");
		tileGrid.setMargin(true);
		
		setContent(mainOpenWindow);
	}
}
