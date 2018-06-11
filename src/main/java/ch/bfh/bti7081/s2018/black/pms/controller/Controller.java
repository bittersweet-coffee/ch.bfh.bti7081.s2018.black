package ch.bfh.bti7081.s2018.black.pms.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.DrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.ClinicModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaUtility;
import ch.bfh.bti7081.s2018.black.pms.view.PatientView.PatientViewListener;

public class Controller {
	
	private static ComboBox<ClinicModel> cmbLocs = new ComboBox<ClinicModel>("Select a Location:");
	private static ComboBox<DoctorModel> cmbDocs = new ComboBox<DoctorModel>("Select your doctor:");

	
	public static ClinicModel getSelectedLocation() {
		Optional<ClinicModel> optional = cmbLocs.getSelectedItem();
		ClinicModel loc = new ClinicModel();
		if (optional.isPresent()) {
			loc = (ClinicModel) optional.get();
		} else {
			loc = null;
		}
		return loc;
	}
	
	public static LinkedList<DoctorModel> getSelectedDoctor() {
		Optional<DoctorModel> selectedItem = cmbDocs.getSelectedItem();
		LinkedList<DoctorModel> docList = new LinkedList<DoctorModel>();
		if (selectedItem.isPresent()) {
			docList.add(selectedItem.get());
		} else {
			docList.addAll(null);
		}
		return docList;
	}
	
	public static void setLocationCombobox(GridLayout grid, int col, int row) {
		cmbLocs.setItems(getLocation());
		cmbLocs.setItemCaptionGenerator(ClinicModel::getName);
		grid.addComponent(cmbLocs, col, row);
	}
	
	public static void setDoctorCombobox(GridLayout grid, int col, int row) {
		cmbDocs.setItems(getDoctors());
		cmbDocs.setItemCaptionGenerator(DoctorModel::getLastname);
		grid.addComponent(cmbDocs, col, row);
	}

	public static Collection<String> getAddictionNames(List<AddictionModel> addicList) {
		List<String> addictionNames = new LinkedList<String>();
		for (AddictionModel addictionModel : addicList) {
			addictionNames.add(addictionModel.getName());
		}
		return addictionNames;
	}

	public static List<AddictionModel> parseSelectedAddictions(Set<String> set) {
		LinkedList<AddictionModel> addicList = new LinkedList<AddictionModel>();
		List<AddictionModel> allAddictsList = new LinkedList<AddictionModel>();
		allAddictsList = getAddictions();
		for (String string : set) {
			for (AddictionModel addictionModel : allAddictsList) {
				if (string.equals(addictionModel.getName())) {
					addicList.add(addictionModel);
				}
			}
		}
		return addicList;
	}
	
	


	public static List<DrugModel> getDrugs(List<PatientViewListener> listeners) {
		List<DrugModel> drugList = getDrugs();
		return drugList;
	}
	

	public static List<DoctorModel> getDoctors() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		return objects.findAll(DoctorModel.class);
	}


	public static List<ClinicModel> getLocation() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		return objects.findAll(ClinicModel.class);
	}

	public static List<AddictionModel> getAddictions() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		return objects.findAll(AddictionModel.class);
	}

	public static List<DrugModel> getDrugs() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		return objects.findAll(DrugModel.class);
	}
	
	public static List<PatientModel> getPatients() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		return objects.findAll(PatientModel.class);
	}
	
}
