package ch.bfh.bti7081.s2018.black.pms.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.vaadin.ui.ComboBox;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.DrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.ClinicModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaUtility;
import ch.bfh.bti7081.s2018.black.pms.view.PatientView.PatientViewListener;

public class Controller {

	public static ClinicModel getSelectedLocation(ComboBox<String> cmbLocs) {
		List<ClinicModel> locModelList = getLocation();
		Optional<String> optional = cmbLocs.getSelectedItem();
		if (optional.isPresent()) {
			for (ClinicModel clinicModel : locModelList) {
				if (optional.get().equals(clinicModel.getName())) {
					return clinicModel;
				}
			}
		}
		return null;
	}

	public static LinkedList<DoctorModel> getSelectedDoctor(ComboBox<String> cmbDocs) {
		List<DoctorModel> docModelList = getDoctors();
		Optional<String> selectedItem = cmbDocs.getSelectedItem();
		LinkedList<DoctorModel> docList = new LinkedList<DoctorModel>();
		if (selectedItem.isPresent()) {
			for (DoctorModel doctorModel : docModelList) {
				if (selectedItem.get().equals(doctorModel.getLastname())) {
					docList.add(doctorModel);
				}
			}
		}
		return docList;
	}

	public static void setupLocations(ComboBox<String> cmbLocs) {
		List<ClinicModel> locModelList = getLocation();
		List<String> locNameList = new LinkedList<String>();
		for (ClinicModel loc : locModelList) {
			locNameList.add(loc.getName());
		}
		cmbLocs.setItems(locNameList);
		cmbLocs.setItemCaptionGenerator(String::toString);
	}

	public static void setupDoctors(ComboBox<String> cmbDocs) {
		List<DoctorModel> docModelList = getDoctors();
		List<String> docNameList = new LinkedList<String>();
		for (DoctorModel doc : docModelList) {
			docNameList.add(doc.getLastname());
		}
		cmbDocs.setItems(docNameList);
		cmbDocs.setItemCaptionGenerator(String::toString);

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
