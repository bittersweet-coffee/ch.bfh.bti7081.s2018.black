package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;
import java.util.Map;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

public interface PatientView {
	
	public interface PatientViewListener {
		void saveButtonClick(PatientItem patient);
		Map<Integer, String> searchButtonClicked(String searchterm);
		Map<Integer, String> setupPatientList();
		List<List<String>> openButtonClicked(Integer patientId, String patientName);
	}
	
	public void addListener(PatientViewListener listener);
}
