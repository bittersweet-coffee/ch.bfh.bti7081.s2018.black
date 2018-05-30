package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;


public interface AddictionView {
	
	public interface AddictionViewListener {
		List<String> searchButtonClicked(String searchTerm);
		List<String> addToButtonClicked();
		void allocateButtonClicked(String addictionName, String patientName);
		List<String> selectListChanged(String addictionName);
		List<String> setupAddictList();
		
	}
	
	public void addListener(AddictionViewListener listener);
}
