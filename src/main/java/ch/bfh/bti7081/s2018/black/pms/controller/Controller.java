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
import ch.bfh.bti7081.s2018.black.pms.model.LocationModel;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaUtility;
import ch.bfh.bti7081.s2018.black.pms.view.PatientView.PatientViewListener;

public class Controller {
	
	private static ComboBox<LocationModel> cmbLocs = new ComboBox<LocationModel>("Select a Location:");
	private static ComboBox<DoctorModel> cmbDocs = new ComboBox<DoctorModel>("Select your doctor:");

	
	public static LocationModel getSelectedLocation() {
		Optional<LocationModel> optional = cmbLocs.getSelectedItem();
		LocationModel loc = new LocationModel();
		if (optional.isPresent()) {
			loc = (LocationModel) optional.get();
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
	
	public static void setLocationCombobox(GridLayout grid, List<PatientViewListener> listeners, int col, int row) {
		cmbLocs.setItems(getLocation(listeners));
		cmbLocs.setItemCaptionGenerator(LocationModel::getName);
		grid.addComponent(cmbLocs, col, row);
	}
	
	public static void setDoctorCombobox(GridLayout grid, List<PatientViewListener> listeners, int col, int row) {
		cmbDocs.setItems(getDoctors(listeners));
		cmbDocs.setItemCaptionGenerator(DoctorModel::getLastname);
		grid.addComponent(cmbDocs, col, row);
	}
	
	
	public static Collection<String> getDrugNames(List<DrugModel> drugList) {
		List<String> drugNames = new LinkedList<String>();
		for (DrugModel drugModel : drugList) {
			drugNames.add(drugModel.getName());
		}
		return drugNames;
	}

	public static Collection<String> getAddictionNames(List<AddictionModel> addicList) {
		List<String> addictionNames = new LinkedList<String>();
		for (AddictionModel addictionModel : addicList) {
			addictionNames.add(addictionModel.getName());
		}
		return addictionNames;
	}
	
	public static List<DrugModel> parseSelectedDrugs(Set<String> selectedItems, List<PatientViewListener> listeners) {
		LinkedList<DrugModel> drugList = new LinkedList<DrugModel>();
		List<DrugModel> allDrugsList = new LinkedList<DrugModel>();
		allDrugsList = getDrugs(listeners);
		for (String string : selectedItems) {
			for (DrugModel drugModel : allDrugsList) {
				if (string.equals(drugModel.getName())) {
					drugList.add(drugModel);
				}
			}
		}
		return drugList;
	}

	public static List<AddictionModel> parseSelectedAddictions(Set<String> set, List<PatientViewListener> listeners) {
		LinkedList<AddictionModel> addicList = new LinkedList<AddictionModel>();
		List<AddictionModel> allAddictsList = new LinkedList<AddictionModel>();
		allAddictsList = getAddictions(listeners);
		for (String string : set) {
			for (AddictionModel addictionModel : allAddictsList) {
				if (string.equals(addictionModel.getName())) {
					addicList.add(addictionModel);
				}
			}
		}
		return addicList;
	}
	
	public static List<DoctorModel> getDoctors(List<PatientViewListener> listeners) {
		List<DoctorModel> docList = new LinkedList<DoctorModel>();
		for (PatientViewListener listener : listeners) {
			docList = listener.getDoctors();
		}
		return docList;
	}

	public static List<LocationModel> getLocation(List<PatientViewListener> listeners) {
		List<LocationModel> locList = new LinkedList<LocationModel>();
		for (PatientViewListener listener : listeners) {
			locList = listener.getLocation();
		}
		return locList;
	}

	public static List<AddictionModel> getAddictions(List<PatientViewListener> listeners) {
		List<AddictionModel> addicList = new LinkedList<AddictionModel>();
		for (PatientViewListener listener : listeners) {
			addicList = listener.getAddictions();
		}
		return addicList;
	}

	public static List<DrugModel> getDrugs(List<PatientViewListener> listeners) {
		List<DrugModel> drugList = new LinkedList<DrugModel>();
		for (PatientViewListener listener : listeners) {
			drugList = listener.getDrugs();
		}
		return drugList;
	}
	
}