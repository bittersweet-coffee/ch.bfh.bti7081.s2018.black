package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

public interface PatientView {
	
	public interface PatientViewListener {
		
		void saveButtonClick(PatientItem patient);
		void searchButtonClicked(String searchterm);

		void setupPatientList();
		
	}
	
	public void setupPatientList(List<String> patientList);
}
