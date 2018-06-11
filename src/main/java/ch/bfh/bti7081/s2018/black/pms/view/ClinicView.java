package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;

public interface ClinicView {
	
	public interface ClinicViewListener {
		List<String> searchButtonClicked(String searchTerm, String searchMode);
		List<String> selectListChanged(String clinicName);
		List<String> setupClinicList();
		
	}
	
	public void addListener(ClinicViewListener listener);
}
