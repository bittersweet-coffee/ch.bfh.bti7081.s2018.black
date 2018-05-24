package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;


public interface AddictionView {
	
	public interface AddictionViewListener {
		void searchButtonClicked(String searchTerm);
		void addToButtonClicked();
		void allocateButtonClicked(String addictionName, String patientName);
		void selectListChanged(String addictionName);
		void setupAddictList();
		
	}
	
	public void addListener(AddictionViewListener listener);
	public void setupAddictList(List<String> addictionList);
	public void setListDesc(String desc);
	public void setListSymptoms(String symptoms);
	public void setupPatientList(List<String> patientList);
}
