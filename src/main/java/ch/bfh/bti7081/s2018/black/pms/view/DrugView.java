package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.Pair;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

public interface DrugView {
	
	public interface DrugViewListener {

		List<String> searchButtonClicked(String searchTerm);
		List<String> addToButtonClicked();
		Pair allocateButtonClicked(String drugName, PatientItem patientItem, Double dose);
		List<String> selectListChanged(String drugName);
		List<String> setupDrugList();
		List<PatientItem> setupPatientItemList();
	}
	
	public void addListener(DrugViewListener listener);
}
