package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;


public interface AddictionView {
	
	public interface AddictionViewListener {
		List<String> searchButtonClicked(String searchTerm);
		List<String> addToButtonClicked();
		void allocateButtonClicked(String addictionName, PatientItem patientName);
		List<String> selectListChanged(String addictionName);
		List<String> setupAddictList();
		List<PatientItem> setupPatientItemList();
		
	}
	
	public void addListener(AddictionViewListener listener);
}
