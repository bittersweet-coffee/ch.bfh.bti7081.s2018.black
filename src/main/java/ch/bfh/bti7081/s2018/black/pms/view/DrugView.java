package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;

public interface DrugView {
	
	public interface DrugViewListener {

		List<String> searchButtonClicked(String searchTerm);
		List<String> addToButtonClicked();
		void allocateButtonClicked(String drugName, String patientName);
		List<String> selectListChanged(String drugName);
		List<String> setupDrugList();
	}
	
	public void addListener(DrugViewListener listener);
}
