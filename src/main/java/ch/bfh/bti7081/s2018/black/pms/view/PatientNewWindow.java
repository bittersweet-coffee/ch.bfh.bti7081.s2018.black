package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.DrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.LocationModel;
import ch.bfh.bti7081.s2018.black.pms.model.NoticeModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;

public class PatientNewWindow extends Window {

	PatientViewImpl view;
	PatientItem patient;
	LocationModel location;
	
	public PatientNewWindow(PatientViewImpl view, PatientItem patientItem, LocationModel location) {
		super("New Patient");
		this.view = view;
		this.patient = patientItem;
		this.location = location;
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
		

		List<DoctorModel> docList = new LinkedList<DoctorModel>();
		List<LocationModel> locList = new LinkedList<LocationModel>();
		List<AddictionModel> addicList = new LinkedList<AddictionModel>();
		List<DrugModel> drugList = new LinkedList<DrugModel>();

		docList = view.getDoctors(docList);
		locList = view.getLocations(locList);
		addicList = view.getAddictions(addicList);
		drugList = view.getDrugs(drugList);
		
		
		
		ComboBox<DoctorModel> cmbDocs = new ComboBox<DoctorModel>("Select your doctor:");
		cmbDocs.setItems(docList);
		cmbDocs.setItemCaptionGenerator(DoctorModel::getLastname);

		ComboBox<LocationModel> cmbLocs = new ComboBox<LocationModel>("Select a Location:");
		cmbLocs.setItems(locList);
		cmbLocs.setItemCaptionGenerator(LocationModel::getName);
		
		TwinColSelect<String> addictionselect = new TwinColSelect<>("Addictions: ");
		addictionselect.setItems(getAddictionNames(addicList));
		addictionselect.setRows(5);

		TwinColSelect<String> drugselect = new TwinColSelect<>("Current medication: ");
		drugselect.setItems(getDrugNames(drugList));
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
				patient.setDoctors(getSelectedDoctors(cmbDocs.getSelectedItem()));
				patient.setAddictions(parseSelectedAddictions(addictionselect.getSelectedItems()));
				patient.setDrugs(parseSelectedDrugs(drugselect.getSelectedItems()));
				patient.setLocation(getSelectedLocation(cmbLocs.getSelectedItem()));
				//patient.setNotes(noticeList);
				view.save(patient, descriptionField.getValue());
				close();
				view.updatePatientItemList();
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

		GridLayout tileGrid = new GridLayout(2, 15);

		tileGrid.setMargin(true);

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
		tileGrid.addComponent(addictionselect, 0, 5);
		tileGrid.addComponent(lblNotes, 0, 6);
		tileGrid.addComponent(descriptionField, 0, 7);
		tileGrid.addComponent(drugselect, 0, 8);
		tileGrid.addComponent(lblDoctors, 0, 9);
		tileGrid.addComponent(cmbDocs, 1, 9);
		tileGrid.addComponent(lblAppointment, 0, 10);
		tileGrid.addComponent(btnAppointment, 1, 10);
		tileGrid.addComponent(lblLocation, 0, 11);
		tileGrid.addComponent(cmbLocs, 1, 12);
		tileGrid.addComponent(btnSave, 0, 13);
		tileGrid.addComponent(btnCancel, 1, 13);
		tileGrid.addComponent(btnDummyData, 0, 14);

		setContent(tileGrid);
	}

	protected LocationModel getSelectedLocation(Optional<LocationModel> selectedItem) {
		LocationModel loc = new LocationModel();
		if (selectedItem.isPresent()) {
			loc = selectedItem.get();
		} else {
			loc = null;
		}
		return loc;
	}

	protected List<DrugModel> parseSelectedDrugs(Set<String> selectedItems) {
		LinkedList<DrugModel> drugList = new LinkedList<DrugModel>();
		List<DrugModel> allDrugsList = new LinkedList<DrugModel>();
		allDrugsList = view.getDrugs(allDrugsList);
		for (String string : selectedItems) {
			for (DrugModel drugModel : allDrugsList) {
				if (string.equals(drugModel.getName())) {
					drugList.add(drugModel);
				}
			}
		}
		return drugList;
	}

	protected List<AddictionModel> parseSelectedAddictions(Set<String> set) {
		LinkedList<AddictionModel> addicList = new LinkedList<AddictionModel>();
		List<AddictionModel> allAddictsList = new LinkedList<AddictionModel>();
		allAddictsList = view.getAddictions(allAddictsList);
		for (String string : set) {
			for (AddictionModel addictionModel : allAddictsList) {
				if (string.equals(addictionModel.getName())) {
					addicList.add(addictionModel);
				}
			}
		}
		return addicList;
	}

	protected List<DoctorModel> getSelectedDoctors(Optional<DoctorModel> selectedItem) {
		LinkedList<DoctorModel> docList = new LinkedList<DoctorModel>();
		if (selectedItem.isPresent()) {
			docList.add(selectedItem.get());
		} else {
			docList.addAll(null);
		}
		return docList;
	}

	private Collection<String> getDrugNames(List<DrugModel> drugList) {
		List<String> drugNames = new LinkedList<String>();
		for (DrugModel drugModel : drugList) {
			drugNames.add(drugModel.getName());
		}
		return drugNames;
	}

	private Collection<String> getAddictionNames(List<AddictionModel> addicList) {
		List<String> addictionNames = new LinkedList<String>();
		for (AddictionModel addictionModel : addicList) {
			addictionNames.add(addictionModel.getName());
		}
		return addictionNames;
	}
}
